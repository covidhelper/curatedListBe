package org.covid19.helper.curatedList;

import org.covid19.helper.curatedList.Constants.ApplicationConstants;
import org.covid19.helper.curatedList.Util.DataCardActionConvertor;
import org.covid19.helper.curatedList.Util.RequestTypeConvertor;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@SpringBootApplication
@RestController
@EnableAsync
@EnableTransactionManagement
public class CuratedListApplication {

	Logger logger = LoggerFactory.getLogger(CuratedListApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CuratedListApplication.class, args);
	}

	@Value("${test}")
	private String hello;

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("thread-");
		executor.initialize();
		return executor;
	}

	@Bean
	public Mapper mapper() {
		return new DozerBeanMapper();
	}

	@RequestMapping("/")
	String home() {
		logger.info("YO YO");
		return "Let's make the world a better place " + hello;
	}

	@InitBinder
	public void initBinder(final WebDataBinder webDataBinder){
		webDataBinder.registerCustomEditor(ApplicationConstants.RequestType.class,
				new RequestTypeConvertor());
		webDataBinder.registerCustomEditor(ApplicationConstants.DataCardAction.class,
				new DataCardActionConvertor());

	}

}
