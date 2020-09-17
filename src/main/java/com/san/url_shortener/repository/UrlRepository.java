package com.san.url_shortener.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.san.url_shortener.entity.UrlEntity;

public interface UrlRepository extends CrudRepository<UrlEntity, String> {
	public List<UrlEntity> findByLongURL(String longUrl);
}
