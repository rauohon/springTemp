package com.a.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.a.*") 
@EnableAsync
@PropertySource("classpath:/properties/local.properties")
public class ServletContext extends WebMvcConfigurerAdapter {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	  
    @Bean
    public TilesConfigurer tilesConfigurer() {
		log.info(">>>>>>>>>>TilesConfigurer");
        final TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(new String[] {"/WEB-INF/tiles/default-layout.xml"});
        configurer.setCheckRefresh(true);
        return configurer;
    }
     
    @Bean
    public UrlBasedViewResolver tilesViewResolver() {
		log.info(">>>>>>>>>>UrlBasedViewResolver");
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(TilesView.class);
        resolver.setOrder(1);
        return resolver;
    }
    
    @Bean
    public InternalResourceViewResolver viewResolver() {
		log.info(">>>>>>>>>>InternalResourceViewResolver");
    	InternalResourceViewResolver viewResolver
    		= new InternalResourceViewResolver();
    	viewResolver.setViewClass(JstlView.class);
    	viewResolver.setPrefix("/WEB-INF/views/");
    	viewResolver.setSuffix(".jsp");
    	viewResolver.setOrder(2);
    	return viewResolver;
    }
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info(">>>>>>>>>>addResourceHandlers");
    	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    	registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    	registry.addResourceHandler("/images/**").addResourceLocations("/images/");
    	registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    	registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
    	registry.addResourceHandler("/less/**").addResourceLocations("/less/");
    	registry.addResourceHandler("/lib/**").addResourceLocations("/lib/");
    }
    
    @Bean(destroyMethod="close")
    public DataSource dataSource() {
    	log.info(">>>>>>>>>>dataSource");
    	BasicDataSource basicDataSource = new BasicDataSource();
    	basicDataSource.setDriverClassName("${db.driver}");
    	basicDataSource.setUrl("${db.url}");
    	basicDataSource.setUsername("${db.username}");
    	basicDataSource.setPassword("${db.password}");
    	basicDataSource.setMaxTotal(30);
    	basicDataSource.setMaxIdle(30);
    	basicDataSource.setMinIdle(5);
    	return basicDataSource;
    }
	
    @Bean
    public SqlSessionFactoryBean sqlSession(DataSource dataSource) throws IOException {
    	log.info(">>>>>>>>>>sqlSession");
    	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    	PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
    	sqlSessionFactoryBean.setDataSource(dataSource);
    	sqlSessionFactoryBean.setConfigLocation(pmrpr.getResource("classpath:/sqlmap/sql-mapper-config.xml"));
    	sqlSessionFactoryBean.setMapperLocations(pmrpr.getResources("classpath:/sqlmap/mappers/**/*.xml"));
    	return sqlSessionFactoryBean;
    }
    
    @Bean
    public MapperScannerConfigurer mapperScanner(DataSource dataSource) {
    	log.info(">>>>>>>>>>mapperScanner");
    	MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    	mapperScannerConfigurer.setBasePackage("**.service.impl");
    	return mapperScannerConfigurer;
    }
    
    @Bean
    public DataSourceTransactionManager txManager(DataSource dataSource) {
    	log.info(">>>>>>>>>>txManager");
    	DataSourceTransactionManager txManager = new DataSourceTransactionManager();
    	txManager.setDataSource(dataSource);
    	return txManager;
    }
    
    @Bean
    public TransactionInterceptor txAdvice() {
    	MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
    	RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
    	transactionAttribute.setName("select*");
    	transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
    	source.setTransactionAttribute(transactionAttribute);
    	TransactionInterceptor txAdvice = new TransactionInterceptor(txManager(dataSource()), source);
    	return txAdvice;
    }
}
