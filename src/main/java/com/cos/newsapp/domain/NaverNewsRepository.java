package com.cos.newsapp.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NaverNewsRepository extends MongoRepository<NaverNews, String>{
	
	/*
	 *  파이썬에서 네이티브 쿼리 만드는 방법(파이썬은 MongoRepository 사용 안됨)
	 *  @Query(value = "{title : ?0, company : ?1}")
	 *  List<NaverNews> mFindByTitleAndCompany(String title, String compnay);
	 */
	
}
