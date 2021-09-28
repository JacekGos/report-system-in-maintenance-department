package com.jacekg.reportSystem.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.jacekg.reportSystem")
@PropertySource("classpath:persistence-postgresql.properties")
public class AppConfig implements WebMvcConfigurer {

	@Autowired
	private Environment environment;

	private Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public MessageSource messageSource () {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages/messages");
		return messageSource;
	}

	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean
	public DataSource appDataSource() {

		ComboPooledDataSource appDataSource = new ComboPooledDataSource();

		try {
			appDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

		logger.info("jdbc.url=" + environment.getProperty("jdbc.url"));
		logger.info("jdbc.user=" + environment.getProperty("jdbc.user"));

		appDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		appDataSource.setUser(environment.getProperty("jdbc.user"));
		appDataSource.setPassword(environment.getProperty("jdbc.password"));

		appDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));

		appDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));

		appDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));

		appDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return appDataSource;
	}

	private int getIntProperty(String propertyName) {

		String propertyValue = environment.getProperty(propertyName);

		int intPropertyValue = Integer.parseInt(propertyValue);

		return intPropertyValue;
	}

	private Properties getHibernateProperties() {

		Properties properties = new Properties();

		properties.setProperty("hibernate_dialect", environment.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

		return properties;
	}

	@Bean
	public LocalSessionFactoryBean appSessionFactory() {
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(appDataSource());
		sessionFactory.setPackagesToScan(environment.getProperty("hiberante.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager appTransactionManager (SessionFactory sessionFactory) {
		
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		
		return txManager;
	}
	
}











