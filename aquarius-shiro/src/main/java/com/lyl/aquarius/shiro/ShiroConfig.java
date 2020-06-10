package com.lyl.aquarius.shiro;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lyl
 * @Package com.lyl.aquarius.shiro
 * @Title: ShiroConfig
 * @Description:
 * @date 2020/4/29 22:08
 */

@Configuration
public class ShiroConfig {

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
//        bean.setLoginUrl("/user/login");
//        bean.setSuccessUrl("/index");
//        bean.setUnauthorizedUrl("/unauthorizedurl");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user/login", "anon");

        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");


        filterChainDefinitionMap.put("/**", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    @Bean
    public Realm aquariusRealm(CredentialsMatcher matcher) {
        AquariusRealm realm = new AquariusRealm();
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    @Bean
    public SessionsSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(aquariusRealm(credentialsMatcher()));
        return securityManager;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroPasswordUtils.ALGORITHM_NAME_MD5);
        hashedCredentialsMatcher.setHashIterations(ShiroPasswordUtils.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }


    /**
     * 会话Cookie 保存sessionID
     *
     * @return simple cookie
     */
    @Bean
    public SimpleCookie sessionIdCookie() {
        //构造方法的参数 是cookie的名称
        SimpleCookie cookie = new SimpleCookie("sid");
        cookie.setHttpOnly(true);
        //关闭浏览器后 ，此cookie清除
        cookie.setMaxAge(-1);
        return cookie;
    }

    /**
     * 会话DAO
     *
     * @return session DAO
     */
    @Bean
    public SessionDAO sessionDAO() {
        MemorySessionDAO sessionDAO = new MemorySessionDAO();
        return sessionDAO;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new
            DefaultWebSessionManager();
        //全局会话超时时间（单位毫秒），默认30分钟
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setSessionDAO(sessionDAO());
        //删除过期的session
        sessionManager.setDeleteInvalidSessions(true);
        //是否开启会话验证器，默认是开启的
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //去掉URL地标后面的JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        //定时清理失效会话, 清理用户直接关闭浏览器造成的孤立会话
        sessionManager.setSessionValidationInterval(1800000);
        //sessionID是否保存到cookie中
        sessionManager.setSessionIdCookieEnabled(true);
        //sessionID Cookie
        sessionManager.setSessionIdCookie(sessionIdCookie());
        return sessionManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
}
