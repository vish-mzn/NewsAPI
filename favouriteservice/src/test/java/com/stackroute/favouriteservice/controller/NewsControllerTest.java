package com.stackroute.favouriteservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.model.News;
import com.stackroute.favouriteservice.services.NewsService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NewsController.class)
public class NewsControllerTest {

	@Autowired
	private transient MockMvc mvc;
	
	@MockBean
	private transient NewsService service;
	
	@InjectMocks
	private NewsController newscontroller;
	
	private transient News news;

	String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aXNoMTIzIiwiaWF0IjoxNTU2MzQzNDA0fQ.X4G-O1ajQZh7sr_C7yO3r_0szjCtVZEWq_4LHlTme9E";
	
	static List<News> newsList;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		news = new News(1, "Title", "publishedAt", "content xyz", "urlToImage", "url", "vish123");
		mvc = MockMvcBuilders.standaloneSetup(newscontroller).build(); 		
		
		newsList = new ArrayList<News>();
		news = new News(1, "Title", "publishedAt", "content xyz", "urlToImage", "url", "vish123");
		newsList.add(news);
		news = new News(2, "Title2", "publishedAt2", "content xyz2", "urlToImage2", "url2", "vish123");
		newsList.add(news);
	}
	
	@Test
	public void testSaveNewNewsSuccess() throws Exception {

		when(service.saveNews(news)).thenReturn(true);
		mvc.perform(post("/api/newsservice/news").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(news))).andExpect(status().isCreated());
		verify(service, times(1)).saveNews(Mockito.any(News.class));
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testDeleteNewsById() throws Exception {
		when(service.deleteNewsById(1)).thenReturn(true);
		mvc.perform(delete("/api/newsservice/news/{id}", 1)).andExpect(status().isOk());
		verify(service, times(1)).deleteNewsById(1);
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testGetMyNews() throws Exception {
		when(service.getMyNews("vish123")).thenReturn(newsList);
		mvc.perform(get("/api/newsservice/news").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		verify(service, times(1)).getMyNews("vish123");
		verifyNoMoreInteractions(service);
	}

	private static String jsonToString(final Object obj) {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}

}
