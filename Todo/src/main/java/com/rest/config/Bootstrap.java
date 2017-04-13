package com.rest.config;

import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.RequestHandledEvent;

import com.rest.dao.TodoDao;
import com.rest.domain.Todo;

@Configuration
@Component
public class Bootstrap implements ApplicationListener<ApplicationEvent> {
	private static final Logger LOGGER = Logger.getLogger(Bootstrap.class
			.getName());

	private boolean applicationStarted = Boolean.FALSE;

	@Resource
	private Environment env;
	@Autowired
	TodoDao<Todo> todoDao;

	@Transactional
	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		
		LOGGER.info("================================= applicationStarted =============================== "
				+ applicationStarted);
		if (applicationEvent instanceof ContextStartedEvent) {
			LOGGER.info("================================= ContextStartedEvent ===============================");
			this.init();
		} else if (applicationEvent instanceof ContextRefreshedEvent) {
			LOGGER.info("================================= ContextRefreshedEvent ===============================");
			this.init();
		} else if (applicationEvent instanceof RequestHandledEvent) {
			LOGGER.info("================================= RequestHandledEvent ===============================");
		} else if (applicationEvent instanceof ContextClosedEvent) {
			LOGGER.info("================================= ContextClosedEvent ===============================");
		} else if (applicationEvent instanceof ContextStoppedEvent) {
			LOGGER.info("================================= ContextStoppedEvent ===============================");
		}
	}

	public void init() {
		if (applicationStarted == Boolean.FALSE) {
			for (Integer i = 1; i < 10; i++) {
				Todo todo = new Todo();
				todo.setName(i.toString() + " Todo");
				todoDao.save(todo);
			}

			LOGGER.info("================================= Data Populate ===============================");
			applicationStarted = Boolean.TRUE;
		}

	}

}
