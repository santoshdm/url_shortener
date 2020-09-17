package com.san.url_shortener;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.san.url_shortener.entity.UrlEntity;
import com.san.url_shortener.model.UrlModel;
import com.san.url_shortener.repository.UrlRepository;
import com.san.url_shortener.service.ShortUrlApiService;
import com.san.url_shortener.service.impl.ShortUrlApiServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SpringBootTest
class OpenAPIConsumerAppTests {

	@Mock
	private UrlRepository urlRepository;

	@InjectMocks
	private ShortUrlApiServiceImpl shortUrlApiServiceImpl;

	@InjectMocks
	private ShortUrlApiService shortUrlApiService;

	@Test
	void testReturnBadRequestIfShortUrlIsNotSet() {
		UrlModel urlModel = new UrlModel();
		ResponseEntity<UrlModel> shortenedURLResponse = shortUrlApiService.getShortenedURL(urlModel);
		assertNotNull(shortenedURLResponse);
		assertEquals(shortenedURLResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	void testReturnSuccessfullForValidData() {
		UrlModel urlModel = new UrlModel();
		urlModel.setLongURL("www.google.com");
		UrlEntity urlEntity = new UrlEntity(1, null, urlModel.getLongURL());
		when(urlRepository.save(urlEntity)).thenReturn(urlEntity);

		ResponseEntity<UrlModel> shortenedURLResponse = shortUrlApiService.getShortenedURL(urlModel);
		assertNotNull(shortenedURLResponse);
		assertEquals(shortenedURLResponse.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	void testReturnConflictForDuplicateData() {
		UrlModel urlModel = new UrlModel();
		urlModel.setLongURL("www.google.com");
		UrlEntity urlEntity = new UrlEntity(1, null, urlModel.getLongURL());
		List<UrlEntity> list = new ArrayList<UrlEntity>();
		list.add(urlEntity);
		when(urlRepository.findByLongURL(urlModel.getLongURL())).thenReturn(list);
		ResponseEntity<UrlModel> shortenedURLResponse = shortUrlApiService.getShortenedURL(urlModel);
		assertNotNull(shortenedURLResponse);
		assertEquals(shortenedURLResponse.getStatusCode(), HttpStatus.CONFLICT);
	}

}
