package com.stackroute.favouriteservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.exception.NewsNotFoundException;
import com.stackroute.favouriteservice.model.News;
import com.stackroute.favouriteservice.repository.NewsRepository;
import com.stackroute.favouriteservice.services.NewsServiceImpl;

public class NewsServiceImplTest {
	
	@Mock
	private transient NewsRepository newsRepo;
	
	private transient News news;
	
	@InjectMocks
	private transient NewsServiceImpl newsServiceImpl;
	
	transient Optional<News> options;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		news = new News(1, "Title", "publishedAt", "content xyz", "urlToImage", "url", "vish123");
		options = Optional.of(news);
	}

	@Test
	public void testMockCreation() {
		assertNotNull("jpaRepository creation fails: use @injectMocks on newsServiceImpl", news);
	}
	
	@Test
	public void testSaveNewsSuccess() throws NewsAlreadyExistsException {
		when(newsRepo.save(news)).thenReturn(news);
		final boolean flag = newsServiceImpl.saveNews(news);
		assertTrue("saving news failed, the call to movieDAOImpl is returning false, check this method", flag);
		verify(newsRepo, times(1)).save(news);
	}

	@Test(expected = NewsAlreadyExistsException.class)
	public void testSaveMovieFailure() throws NewsAlreadyExistsException {
		when(newsRepo.findById(1)).thenReturn(options);
		when(newsRepo.save(news)).thenReturn(news);
		final boolean flag = newsServiceImpl.saveNews(news);
		assertFalse("Saving news failed", flag);
		verify(newsRepo, times(1)).findById(news.getId());
	}

	@Test
	public void testDeleteNewsById() throws NewsNotFoundException {
		when(newsRepo.findById(1)).thenReturn(options);
		doNothing().when(newsRepo).delete(news);
		final boolean flag = newsServiceImpl.deleteNewsById(1);
		assertTrue("deleting news failed", flag);
		verify(newsRepo, times(1)).delete(news);
		verify(newsRepo, times(1)).findById(news.getId());
	}

	@Test
	public void testGetMyNews()  {
		final List<News> newsList = new ArrayList<News>();
		newsList.add(news);
		when(newsRepo.findByUserId(news.getUserId())).thenReturn(newsList);
		final List<News> newsList1 = newsServiceImpl.getMyNews("vish123");
		assertEquals(newsList, newsList1);
		verify(newsRepo, times(1)).findByUserId("vish123");
	}
	
}
