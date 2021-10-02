package com.cos.newsapp.batch;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.newsapp.domain.NaverNews;
import com.cos.newsapp.domain.NaverNewsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NaverNewsCrawBatch {
	
	private int aid = 1;
	
	private final NaverNewsRepository naverNewsRepository;
	
	@Scheduled(fixedDelay = 1000*60*1)
	public void newsCraw() {
		
		// 벌크 컬렉터로 for문 돌때마다 저장하지 않고 한꺼번에 저장, class타입으로 데이터 세가지 담을 수 있게 NaverNews 모델 만들어준다.
		List<NaverNews> newsList = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			String aidStr = String.format("%010d", aid);
			String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=102&oid=022&aid=" + aidStr;
			
			RestTemplate rt = new RestTemplate(); // 안드로이드 : Retrofit2(내부 쓰레드)

			String html = rt.getForObject(url, String.class); // String.class 응답받은 타입
			
			Document doc = Jsoup.parse(html);
			
			Element companyElement = doc.selectFirst(".press_logo img");
			String company = companyElement.attr("title");
			
			Element titleElement = doc.selectFirst("#articleTitle");
			String title = titleElement.text();
			
			Element createAtElement = doc.selectFirst(".t11");
			String createAt = createAtElement.text();
			
			/* 모델에서 @AllArgsConstructor, @Data 추가  
			 * 생성자를 사용해서 데이터를 넣어준다. -> 일일이 하나씩 넣는 거 보다 편하다. 
			 *  Builder를 사용하면 _id 안 넣어도 되고 적는 순서를 지키지 않아도 된다.
			 */
			
			NaverNews nn = NaverNews.builder()
					.company(company)
					.title(title)
					.createAt(createAt)
					.build();
			
			newsList.add(nn);
			
			aid++;
			
		} // end of for
		
		naverNewsRepository.saveAll(newsList); // 벌크 컬렉터  : 한번에 저장 -> 엄청 빠르다. 
		
		/*
		 * 참고 : PL_SQL의 Bulk Collect -> Oracle에서 코딩할 수 있다.
		 */
		
	} // end of newsCraw()
}
