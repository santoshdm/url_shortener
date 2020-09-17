package com.san.url_shortener.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class AppStartupRunner implements ApplicationRunner {
	private static final Logger LOG = LoggerFactory.getLogger(AppStartupRunner.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOG.info("Application started with option names : {}", args.getOptionNames());
		LOG.info("Start up tasks goes here...");
	}
}
