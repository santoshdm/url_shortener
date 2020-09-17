package com.san.url_shortener.service.impl;

import org.springframework.stereotype.Component;

@Component
public class ShortUrlApiServiceImpl {

	public String idToShortURL(int n) {
		try {
			char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
			StringBuffer shorturl = new StringBuffer();
			while (n > 0) {
				shorturl.append(map[n % 62]);
				n = n / 62;
			}
			return shorturl.reverse().toString();
		} catch (Exception e) {
			return null;
		}
	}

}
