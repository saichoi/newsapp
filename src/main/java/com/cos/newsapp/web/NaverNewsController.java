package com.cos.newsapp.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.newsapp.domain.NaverNews;
import com.cos.newsapp.domain.NaverNewsRepository;
import com.cos.newsapp.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController // 데이터 리턴
public class NaverNewsController {
	
	private final NaverNewsRepository naverNewsRepository;
	
	@GetMapping("/naverNews")
	public CMRespDto<?> findAll(){
		// 데이터 제대로 받았는지 구분할 수 있게 공통 DTO를 만든다.
		// 묵시적 타입 : CMRespDto<List<NaverNews>> -> CMRespDto<?> -> ? : 타입 추론
		// 응답할때 CMRespDto 대신 ResponseEntity를 사용하기도 한다.(고급)
		List<NaverNews> naverNewsList = naverNewsRepository.findAll();
		return new CMRespDto<>(1, "성공", naverNewsList); // 값을 넣을 때(리턴 시) 타입이 정해진다.(동적 리턴 가능)
	}
	
}
