package com.stackroute.favouriteservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.model.News;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class NewsRepositoryTest {
	
	@Autowired
	private transient NewsRepository newsRepository;
	
	public NewsRepository getNewsRepository() {
		return newsRepository;
	}
	public void setNewsRepository(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}
	
	private News news;
	
	@Before
	public void setUp() {
		news = new News(1, "Title", "publishedAt", "content xyz", "urlToImage", "url", "vish123");
		newsRepository.save(news);
	}
	
	@After
	public void deleteDatabase() {
		newsRepository.deleteAllInBatch();
	}
	
	@Test
	public void testSaveNews() throws Exception {
		newsRepository.save(new News(1, "NewsTitle", "publishedAt", "content xyz", "urlToImage", "url", "vish123"));
		final News fetchedNews = newsRepository.findByTitle("NewsTitle");
		assertThat(fetchedNews.getTitle()).isEqualTo("NewsTitle");
	}

	@Test
	public void testGetMyNews() throws Exception {
		final List<News> myNews = newsRepository.findByUserId("vish123");
		assertEquals(myNews.size(), 1);
	}

	@Test
	public void testDeleteNews() throws Exception {
		News fetchednews=null;
		fetchednews = newsRepository.findByTitle("Title");
		assertEquals("Title", fetchednews.getTitle());
		newsRepository.delete(fetchednews);
		assertThat(newsRepository.findByTitle("Title")).isEqualTo(null);
		/*assertEquals(Optional.empty(), newsRepository.findByTitle("Title"));*/
	}

}
