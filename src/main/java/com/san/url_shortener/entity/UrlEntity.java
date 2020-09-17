package com.san.url_shortener.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UrlEntity {
	@Id
	@GeneratedValue
	private int id;
	private String shortURL;
	private String longURL;

	public UrlEntity() {
		super();
	}

	public UrlEntity(int id, String shortURL, String longURL) {
		super();
		this.id = id;
		this.shortURL = shortURL;
		this.longURL = longURL;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShortURL() {
		return shortURL;
	}

	public void setShortURL(String shortURL) {
		this.shortURL = shortURL;
	}

	public String getLongURL() {
		return longURL;
	}

	public void setLongURL(String longURL) {
		this.longURL = longURL;
	}

}
