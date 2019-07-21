package com.stackroute.favouriteservice.services;

import java.util.List;

import com.stackroute.favouriteservice.exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.exception.NewsNotFoundException;
import com.stackroute.favouriteservice.model.News;

public interface NewsService {

	boolean saveNews(News news) throws NewsAlreadyExistsException;

	boolean deleteNewsById(int id) throws NewsNotFoundException;

	List<News> getMyNews(String userId);

}
