package com.a.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/properties/local.properties")
public class DataSourceConfig implements EnvironmentAware {
	
	private Environment env; // env @Autowire 타이밍 문제로 EnvironmentAware를 implements 함
	/*
	 * 2018.09.29
	 * @Value 사용하지 못함
	 * @Value 사용시 PropertySourcesPlaceholderConfigurer 필요
	 * @Value("${db.url}")
	 * private String value; 
	*/
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}
	
	/*@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
		//pspc.setIgnoreUnresolvablePlaceholders(true);
		//pspc.setLocation(new ClassPathResource("properties/local.properties"));
	}*/
	
    @Bean
    public DataSource dataSourceSpied() {
    	log.info(">>>>>>>>>>dataSource");
    	try {
        	BasicDataSource basicDataSource = new BasicDataSource();
        	basicDataSource.setDriverClassName(env.getProperty("db.driver"));
        	basicDataSource.setUrl(env.getProperty("db.url"));
        	basicDataSource.setUsername(env.getProperty("db.username"));
        	basicDataSource.setPassword(env.getProperty("db.password"));
        	basicDataSource.setMaxTotal(30);
        	basicDataSource.setMaxIdle(30);
        	basicDataSource.setMinIdle(5);
        	return basicDataSource;
		} catch (Exception e) {
			log.error("DataSourceConfig.getDataSource()" + e.getMessage());
		}
    	
    	return null;
    }
    
    /* aop 설정 실패로 미사용
     * @Bean
    public PlatformTransactionManager transactionManager() {
    	return new DataSourceTransactionManager(getDataSource());
    }*/
    
}
