package com.a.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
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
    
}
