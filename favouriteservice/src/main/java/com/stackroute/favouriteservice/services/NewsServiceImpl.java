package com.stackroute.favouriteservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.exception.NewsNotFoundException;
import com.stackroute.favouriteservice.model.News;
import com.stackroute.favouriteservice.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService{
	
	private final NewsRepository newsRepo;
	
	@Autowired
	public NewsServiceImpl(final NewsRepository newsRepo) {
		super();
		this.newsRepo = newsRepo;
	}
	

	@Override
	public boolean saveNews(News news) throws NewsAlreadyExistsException {
		final Optional<News> object = newsRepo.findById(news.getId());
		if(object.isPresent()) {
			throw new NewsAlreadyExistsException("Could Not save News, already exists");
		}
		newsRepo.save(news);
		return true;
	}

	
	@Override
	public boolean deleteNewsById(int id) throws NewsNotFoundException {
		final News news = newsRepo.findById(id).orElse(null);
		if(news == null) {
			throw new NewsNotFoundException("Could Not delete, News not found!");
		}
		newsRepo.delete(news);
		return true;
	}
	

	@Override
	public List<News> getMyNews(String userId) {
		return newsRepo.findByUserId(userId);
	}

}
