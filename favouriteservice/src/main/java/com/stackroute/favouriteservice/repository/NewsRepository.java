package com.stackroute.favouriteservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favouriteservice.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer>{
	
	List<News> findByUserId(String userId);
	
	News findByTitle(String title);

}
