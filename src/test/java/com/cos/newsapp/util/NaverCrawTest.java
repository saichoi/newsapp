package com.cos.newsapp.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class NaverCrawTest {

	int aid = 1;
	
	@Test
	public void test() {
		String aidStr = String.format("%010d", aid);
		String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=102&oid=022&aid=" + aidStr;
		
		RestTemplate rt = new RestTemplate(); // 안드로이드 : Retrofit2(내부 쓰레드)
		

		/*  
		 * json 데이터를 받을 경우
		 * 		{"username":"sai", "password":"1234"} 
		 * 
		 * rt.getForObject 대신 rt.exchange 사용할 수 있다. -> json 데이터를 파싱해서 바로 받음
		 * 		requestEntity : 요청하는 객체,  데이터를 주고 싶을 때(POST, PUT)
		 * 		responseType : 어떠한 요청에 대한 응답할 때(CMRespDto 담아서) -> 응답은 json -> 파싱해서 바로 받아줌
		 * 
		 * class를 파싱해서 바로 받기 
		 * 		ResponseEntity<UserDto> dto = rt.getForEntity(url, UserDto.class);
		 * 		dto.getBody();
		 */
		
		String html = rt.getForObject(url, String.class); // String.class 응답받은 타입
		//System.out.println(html);
		
		Document doc = Jsoup.parse(html);
		
		Element companyElement = doc.selectFirst(".press_logo img");
		String company = companyElement.attr("title");
		System.out.println(company);
		
		Element titleElement = doc.selectFirst("#articleTitle");
		String title = titleElement.text();
		System.out.println(title);
		
		Element createAtElement = doc.selectFirst(".t11");
		String createAt = createAtElement.text();
		System.out.println(createAt);
		
	}
	
	
	
}

//@Data
//class UserDto{
//	private String username;
//	private String password;
//}
