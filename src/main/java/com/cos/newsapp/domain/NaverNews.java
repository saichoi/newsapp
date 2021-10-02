package com.cos.newsapp.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
@Document(collection = "naver_news") // 몽고디비 컬렉션 이름 지정
public class NaverNews {
	private String _id;
	private String company;
	private String title;
	private String createAt; // 날짜 몽고디비에 save()하면 무조건 미국시간으로 들어감!! +9시간 되서 들어감.
}
