package com.san.url_shortener.service;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.san.url_shortener.api.ShortUrlApiDelegate;
import com.san.url_shortener.entity.UrlEntity;
import com.san.url_shortener.model.UrlModel;
import com.san.url_shortener.repository.UrlRepository;
import com.san.url_shortener.service.impl.ShortUrlApiServiceImpl;

/**
 * @author santosh
 */
@Service
public class ShortUrlApiService implements ShortUrlApiDelegate {
	private static final Logger LOG = LoggerFactory.getLogger(ShortUrlApiService.class);

	@Autowired
	private ShortUrlApiServiceImpl shortUrlApiServiceImpl;

	@Autowired
	private UrlRepository urlRepository;

	private DozerBeanMapper mapper = new DozerBeanMapper();

	@Override
	public ResponseEntity<UrlModel> getShortenedURL(UrlModel urlModel) {
		LOG.info("Request Data " + urlModel.getLongURL());

		if (urlModel.getLongURL() == null) {
			LOG.info("Bad Request >>> null URL");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		List<UrlEntity> urlEntityList = urlRepository.findByLongURL(urlModel.getLongURL());
		if (urlEntityList != null && urlEntityList.size() > 0) {
			LOG.info("URL is already shortened >>> " + urlEntityList.get(0).getLongURL());
			return new ResponseEntity<UrlModel>(mapper.map(urlEntityList.get(0), UrlModel.class), HttpStatus.CONFLICT);
		}

		UrlEntity urlEntity = urlRepository.save(mapper.map(urlModel, UrlEntity.class));
		LOG.info("Generated ID >>> " + urlEntity.getId());

		String shortnedURL = shortUrlApiServiceImpl.idToShortURL(urlEntity.getId());
		LOG.info("ShortnedURL >>> " + shortnedURL);
		if (shortnedURL == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		urlEntity.setShortURL(shortnedURL);
		urlModel = mapper.map(urlEntity, UrlModel.class);

		return new ResponseEntity<UrlModel>(urlModel, HttpStatus.CREATED);
	}
}
