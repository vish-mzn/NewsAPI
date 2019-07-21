package com.stackroute.favouriteservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "published_time")
	private String publishedAt;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "image_url")
	private String urlToImage;
	
	@Column(name = "news_link")
	private String url;
	
	@Column(name = "user_id")
	private String userId;
	

	public News() {
		super();
	}

	public News(int id, String title, String publishedAt, String content, String urlToImage, String url,
			String userId) {
		super();
		this.id = id;
		this.title = title;
		this.publishedAt = publishedAt;
		this.content = content;
		this.urlToImage = urlToImage;
		this.url = url;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", publishedAt=" + publishedAt + ", content=" + content
				+ ", urlToImage=" + urlToImage + ", url=" + url + ", userId=" + userId + "]";
	}
	
}
