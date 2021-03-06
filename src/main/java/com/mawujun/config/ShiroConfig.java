package com.mawujun.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.mawujun.controller.shiro.ShiroFormAuthenticationFilter;
import com.mawujun.controller.shiro.ShiroKickoutSessionControlFilter;
import com.mawujun.controller.shiro.ShiroURLPermissionsFilter;
import com.mawujun.permission.ShiroApplicationListener;
import com.mawujun.permission.ShiroAuthorizingRealm;

/**
 * http://blog.aquariuslt.com/2015/10/25/apache-shiro-spring-integration/
 */
@Configuration
//@EnableAspectJAutoProxy(proxyTargetClass=true)
//@ComponentScan(basePackages = "com.mawujun", excludeFilters = @Filter(type = FilterType.ANNOTATION, value = { Controller.class }))
//@MapperScan(basePackages = "com.mawujun")
//@PropertySource("classpath:config.properties")
public class ShiroConfig {
	
	@Bean
	public ShiroApplicationListener ShiroApplicationListener(){
		ShiroApplicationListener shiroApplicationListener= new ShiroApplicationListener();
		return shiroApplicationListener;
	}

	@Bean
	public DefaultWebSecurityManager securityManager(){
		DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}
	@Bean
	public Realm shiroRealm(){
		ShiroAuthorizingRealm shiroRealm=new ShiroAuthorizingRealm();

		shiroRealm.setCacheManager(shiroCacheManager());
		
		return shiroRealm;
	}
//	@Bean
//	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
//		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
//		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
//		return authorizationAttributeSourceAdvisor;
//	}
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
		LifecycleBeanPostProcessor lifecycleBeanPostProcessor=new LifecycleBeanPostProcessor();
		
		return lifecycleBeanPostProcessor;
	}
	
	@Bean
	public ShiroURLPermissionsFilter shiroURLPermissionsFilter(){
		ShiroURLPermissionsFilter shiroURLPermissionsFilter=new ShiroURLPermissionsFilter();
		shiroURLPermissionsFilter.addIgnoreUrl("/main/**");
		shiroURLPermissionsFilter.addIgnoreUrl("/user/login.do*");
		shiroURLPermissionsFilter.addIgnoreUrl("/user/logout.do*");
		return shiroURLPermissionsFilter;
	}
	
	@Bean
	public ShiroFormAuthenticationFilter shiroFormAjaxAuthenticationFilter(){
		ShiroFormAuthenticationFilter formAjaxAuthenticationFilter=new ShiroFormAuthenticationFilter();
		return formAjaxAuthenticationFilter;
	}
	
	
	@Bean//(name = "shiroFilter")
	public Filter shiroFilter() throws Exception{
		ShiroFilterFactoryBean shiroFilter=new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager());
		shiroFilter.setLoginUrl("/main/login.jsp");
		shiroFilter.setSuccessUrl("/main/index.jsp");
		shiroFilter.setUnauthorizedUrl("/main/unauthorized.jsp");
		
		
//		Map<String,Filter> filters=new HashMap<String,Filter>();
//		filters.put("authc", new ShiroFormAjaxAuthenticationFilter()());
//		filters.put("perms", new ShiroURLPermissionsFilter()());	
		//filters.put("kickout", shiroKickoutSessionControlFilter);
		//shiroFilter.setFilters(filters);
		
		
		ShiroKickoutSessionControlFilter shiroKickoutSessionControlFilter=new ShiroKickoutSessionControlFilter();
		shiroKickoutSessionControlFilter.setKickoutUrl("/main/login.jsp");
		//shiroKickoutSessionControlFilter.setMaxSession(1);
		shiroKickoutSessionControlFilter.setKickoutAfter(false);
		shiroKickoutSessionControlFilter.setCacheManager(shiroCacheManager());
		shiroKickoutSessionControlFilter.setSessionManager(sessionManager());

		shiroFilter.getFilters().put("kickout", shiroKickoutSessionControlFilter);
		shiroFilter.getFilters().put("authc", shiroFormAjaxAuthenticationFilter());
		shiroFilter.getFilters().put("perms", shiroURLPermissionsFilter());
		

		Map<String,String> filterChainDefinitionMap=new HashMap<String,String>();
		//filterChainDefinitionMap.put("/**", "anon");//这个是临时的
		filterChainDefinitionMap.put("/", "anon");//anon只是用于判断认证，不用于判断授权
		filterChainDefinitionMap.put("/**/*.html", "anon");
		filterChainDefinitionMap.put("/main/login.jsp*", "anon");
		filterChainDefinitionMap.put("/menu/queryElementPermission.do*", "anon");
		//filterChainDefinitionMap.put("/ord/mobile/yxgs/getOrstat.do*", "anon");

		
		//filterChainDefinitionMap.put("/mobile/redirect_yxgshtml.do*", "anon");
		//filterChainDefinitionMap.put("/mobile/login/queryMobileMenuByUser.do*", "anon");
		filterChainDefinitionMap.put("//mobile/login/logout.do*", "anon");
		filterChainDefinitionMap.put("/mobile/login/login.do*", "anon");
		
		filterChainDefinitionMap.put("/main/unauthorized.jsp*", "anon");
		filterChainDefinitionMap.put("/user/login.do*", "anon");
		filterChainDefinitionMap.put("/user/onlineling.do*", "anon");
		
		//filterChainDefinitionMap.put("/user/mobile/getWxConfig.do*", "anon");
		//filterChainDefinitionMap.put("/ord/mobile/getOrdmt.do*", "anon");
		filterChainDefinitionMap.put("/**/*.css", "anon");
		filterChainDefinitionMap.put("/**/*.js", "anon");
		filterChainDefinitionMap.put("/**/*.gif", "anon");
		filterChainDefinitionMap.put("/**/*.jpg", "anon");
		filterChainDefinitionMap.put("/**/*.png	", "anon");
	
		filterChainDefinitionMap.put("/**/*.jsp*", "authc,perms,kickout");//拦截哪些地址,进入哪些指定的过滤器
		filterChainDefinitionMap.put("/**/*.do*", "authc,perms,kickout");
		//filterChainDefinitionMap.put("/**", "authc");
		//filterChainDefinitionMap.put("", "");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return (Filter)shiroFilter.getObject();
		
	}
	


	

	
//	@Bean
//    public EhCacheManager cacheManager() {  
//        EhCacheManager em = new EhCacheManager();  
//        //em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
//        em.setCacheManagerConfigFile("classpath:ehcache.xml");  
//        return em;  
//    } 
	
//	@Resource(name="cacheManagerFactory")
//	EhCacheManagerFactoryBean ehCacheManagerFactoryBean;
	
	
	@Bean(name = "cacheManagerFactory")
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		Resource resource = new ClassPathResource("ehcache.xml");
		ehCacheManagerFactoryBean.setConfigLocation(resource);
		ehCacheManagerFactoryBean.setShared(true);
		return ehCacheManagerFactoryBean;
	}
	@Bean
    public EhCacheManager shiroCacheManager() {  
        EhCacheManager em = new EhCacheManager();  
        //em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        //em.setCacheManagerConfigFile("classpath:ehcache.xml"); 
        em.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return em;  
    } 
	
	//回话管理部分
	/**
	 * 会话Cookie模板 
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @return
	 */
	@Bean
	public SimpleCookie sessionIdCookie(){
		SimpleCookie simpleCookie=new SimpleCookie("sid");
		simpleCookie.setHttpOnly(true);
		simpleCookie.setMaxAge(6000000);
		return simpleCookie;
	}
	@Bean
	public JavaUuidSessionIdGenerator sessionIdGenerator() {
		JavaUuidSessionIdGenerator sessionIdGenerator=new JavaUuidSessionIdGenerator();
		return sessionIdGenerator;
	}
	@Bean
	public EnterpriseCacheSessionDAO sessionDAO() {
		EnterpriseCacheSessionDAO sessionDAO=new EnterpriseCacheSessionDAO();
		sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
		sessionDAO.setSessionIdGenerator(sessionIdGenerator());
		return sessionDAO;
	}	
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(3600000);//
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		//sessionManager.setSessionValidationScheduler(sessionValidationScheduler());
		sessionManager.setSessionDAO(sessionDAO());
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionIdCookie(sessionIdCookie());
		//监听会话的开始，结束的监听器，实现SessionListenerAdapter方类
		//sessionManager.setSessionListeners();
		return sessionManager;
	}
	/**
	 * 会话验证调度器
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @return
	 */
	@Bean
	public ExecutorServiceSessionValidationScheduler sessionValidationScheduler() {
		ExecutorServiceSessionValidationScheduler sessionValidationScheduler=new ExecutorServiceSessionValidationScheduler();
		sessionValidationScheduler.setInterval(120000);
		sessionValidationScheduler.setSessionManager( sessionManager());
		return sessionValidationScheduler;
	}
	
	
	
}
