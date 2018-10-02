package com.a.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
@ImportResource("classpath:/spring/context-transaction.xml")
public class TransactionConfig {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	//private static final int TX_METHOD_TIMEOUT = 5;
	
    @Bean
    public DataSourceTransactionManager txManager(DataSource dataSource) {
    	log.info(">>>>>>>>>>txManager");
    	DataSourceTransactionManager txManager = new DataSourceTransactionManager();
    	txManager.setDataSource(dataSource);
    	return txManager;
    }
    
    /*@Bean
    public TransactionInterceptor txAdvice() {
    	log.info(">>>>>>>>>>txAdvice");
    	
    	MatchAlwaysTransactionAttributeSource txaSource = new MatchAlwaysTransactionAttributeSource();
    	
    	RuleBasedTransactionAttribute txAttribute = new RuleBasedTransactionAttribute();
    	
    	txAttribute.setName("*");
    	txAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
    	
    	txaSource.setTransactionAttribute(txAttribute);
    	
    	TransactionInterceptor txAdvice = new TransactionInterceptor();
    	
    	txAdvice.setTransactionAttributeSource(txaSource);
		txAdvice.setTransactionManager(transactionManager);
    	
    	return txAdvice;
    	
    	TransactionInterceptor txAdvice = new TransactionInterceptor();
    	Properties txAttributes = new Properties();
    	    	
		String readOnlyTransactionAttributesDefinition = readOnlyAttribute();

		String writeTransactionAttributesDefinition = writeAttribute();
    	
		log.info("Read Only Attributes :: {}", readOnlyTransactionAttributesDefinition);
		log.info("Write Attributes :: {}", writeTransactionAttributesDefinition);
    	
		// read-only
		txAttributes.setProperty("select*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("get*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("search*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("find*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("count*", readOnlyTransactionAttributesDefinition);

		// write rollback-rule
		txAttributes.setProperty("insert*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("update*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("delete*", writeTransactionAttributesDefinition);
		
		txAdvice.setTransactionAttributes(txAttributes);
		txAdvice.setTransactionManager(transactionManager);

        return txAdvice; 
    }*/
    
    /*@Bean
    public Advisor txAdviceAdvisor() {
    	log.info(">>>>>>>>>>txAdviceAdvisor");
    	AspectJExpressionPointcut pointCut = new AspectJExpressionPointcut();
    	pointCut.setExpression("(execution(* com.a.b.service.impl.*Impl.*(..))");
    	
    	*
    	 * 1. * : 지시자(?), public private 등
    	 * 2. com. : com 패키지 밑에
    	 * 3. *..*. : 사이 많은 패키지 밑에
    	 * 4. service.. :  service 패키지와 하위 패키지 밑에
    	 * 5. impl. : impl 패키지 밑에
    	 * 6. *Impl : Impl로 끝나는 클래스 밑에
    	 * 7. *(..) : 인자값이 0개 이상인 메서드
    	 * *
    	return new DefaultPointcutAdvisor(pointCut, txAdvice());
    }*/
    /*
	private String readOnlyAttribute() {
		log.info(">>>>>>>>>>readOnlyAttribute");
    	DefaultTransactionAttribute readOnlyAttribute 
			= new DefaultTransactionAttribute();
		readOnlyAttribute.setReadOnly(true);
		readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);
	
		return readOnlyAttribute.toString();
	}*/
    /*
	private String writeAttribute() {
		log.info(">>>>>>>>>>writeAttribute");
    	List<RollbackRuleAttribute> rollbackRules = new ArrayList<RollbackRuleAttribute>();
    	rollbackRules.add(new RollbackRuleAttribute(Exception.class));
    	
		RuleBasedTransactionAttribute writeAttribute
			= new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
		writeAttribute.setTimeout(TX_METHOD_TIMEOUT);
		
		return writeAttribute.toString();
	}*/
}
