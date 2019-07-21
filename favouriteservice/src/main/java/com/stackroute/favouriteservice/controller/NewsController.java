package com.stackroute.favouriteservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.exception.NewsNotFoundException;
import com.stackroute.favouriteservice.model.News;
import com.stackroute.favouriteservice.services.NewsService;

import io.jsonwebtoken.Jwts;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/newsservice")
public class NewsController {
	
	private NewsService newsService;
	
	@Autowired
	private NewsController(final NewsService newsService) {
		this.newsService = newsService;
	}
	
	
	// <-- Save Movie Method -->
	@PostMapping("/news")
	public ResponseEntity<?> saveNews(@RequestBody final News news, HttpServletRequest request) 
	{
		final String authHeader = request.getHeader("Authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();

		ResponseEntity<?> responseEntity;
		try {
			news.setUserId(userId);
			if(news.getContent().length()>200)
				news.setContent(news.getContent().substring(0, 200));
			else if(news.getContent().equals(null))
				news.setContent(null);
			else
				news.setContent(news.getContent());
			newsService.saveNews(news);
			responseEntity = new ResponseEntity<News>(news, HttpStatus.CREATED);
		} catch (NewsAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	
	// <-- Delete Movie Method -->
	@DeleteMapping("/news/{id}")
	public ResponseEntity<?> deleteNewsById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		try {
			newsService.deleteNewsById(id);
			responseEntity = new ResponseEntity<String>("news deleted successfully", HttpStatus.OK);
		} catch (NewsNotFoundException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
 
	
	// <-- fetching user watchlist -->
	@GetMapping("/news")
	public ResponseEntity<?> getMyNews(HttpServletRequest request) {
		final String authHeader = request.getHeader("Authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();

		List<News> movieList = newsService.getMyNews(userId);
		if(movieList.isEmpty()) {
			return new ResponseEntity<String>("No news existed!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<News>>(movieList, HttpStatus.OK);
	}

}
