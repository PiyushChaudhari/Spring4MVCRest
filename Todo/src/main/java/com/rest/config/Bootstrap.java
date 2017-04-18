package com.rest.config;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
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
import com.rest.dao.UserDao;
import com.rest.domain.Todo;
import com.rest.domain.User;

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
	@Autowired
	UserDao<User> userDao;

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {

		LOGGER.info("================================= applicationStarted =============================== "
				+ applicationStarted);
		if (applicationEvent instanceof ContextStartedEvent) {
			LOGGER.info("================================= ContextStartedEvent ===============================");
			// this.init();
		} else if (applicationEvent instanceof ContextRefreshedEvent) {
			LOGGER.info("================================= ContextRefreshedEvent ===============================");
			// this.init();
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

			// User user = new User();
			// user.setFirstName("Piyush");
			// user.setLastName("Chaudhari");
			// user.setEmail("piyu1812@yahoo.co.in");
			// user.setPassword("111111111");

			// String id = userDao.save(user);;
			// User loadUser = userDao.save(user);
			// System.out.println(loadUser);
			//
			// Todo todo = new Todo();
			// todo.setName("1 todo");
			// todo.setUser(loadUser);
			// Set<Todo> todoList = new HashSet<Todo>();
			// todoList.add(todo);
			// user.setTodoList(todoList);
			// todoDao.save(todo);

			for (Integer i = 1; i <= 50; i++) {

				User user = new User();
				user.setFirstName("Piyush " + i.toString());
				user.setLastName("Chaudhari " + i.toString());
				user.setEmail("piyu" + i.toString() + "@gmail.com");
				user.setPassword("111111111");

				for (Integer j = i; j <= 5000; j++) {
					Todo todo = new Todo();
					todo.setName("Todo " + j.toString());
					todo.setUser(user);
					Set<Todo> todoList = new HashSet<Todo>();
					todoList.add(todo);
					user.setTodoList(todoList);
					todoDao.save(todo);
				}

			}

			LOGGER.info("================================= Data Populate ===============================");
			applicationStarted = Boolean.TRUE;
		}

	}
}
