package com.dstz.agilebpm.security.autoconfiguration;

import com.dstz.security.authentication.JWTAuthenticationFilter;
import com.dstz.security.filter.RefererCsrfFilter;
import com.dstz.security.filter.RequestThreadFilter;
import com.dstz.security.filter.XssFilter;
import com.dstz.security.forbidden.DefaultAccessDeniedHandler;
import com.dstz.security.forbidden.DefualtAuthenticationEntryPoint;
import com.dstz.security.jwt.service.JWTService;
import com.dstz.security.login.CustomPwdEncoder;
import com.dstz.security.login.UserDetailsServiceImpl;
import com.dstz.security.login.logout.DefualtLogoutSuccessHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

/**
 * ab web jwt 自动配置
 *
 * @author wacxhs
 */
//@EnableConfigurationProperties({AbSecurityProperties.class})
public class AbWebJwtSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private AbSecurityProperties abSecurityProperties;

    public AbWebJwtSecurityConfiguration(AbSecurityProperties abSecurityProperties) {
        this.abSecurityProperties = abSecurityProperties;
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {

        return new JWTAuthenticationFilter();
    }

    @Bean
    public JWTService jwtService() {

        return new JWTService();
    }

    @Bean
    public DefualtAuthenticationEntryPoint defualtAuthenticationEntryPoint() {

        return new DefualtAuthenticationEntryPoint();
    }

    @Bean
    public DefaultAccessDeniedHandler defaultAccessDeniedHandler() {

        return new DefaultAccessDeniedHandler();
    }

    @Bean
    public DefualtLogoutSuccessHandler defualtLogoutSuccessHandler() {

        return new DefualtLogoutSuccessHandler();
    }

    @Bean
    public CookieLocaleResolver cookieLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.CHINA);
        return cookieLocaleResolver;
    }

    @Bean
    public FilterRegistrationBean xssFilter() {
        XssFilter xssFilter = new XssFilter();
        if (StringUtils.isNotEmpty(abSecurityProperties.getXssIngores())) {
            xssFilter.setIngores(Arrays.asList(StringUtils.split(abSecurityProperties.getXssIngores(), ",")));
        }

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(xssFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("xssFilter");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean refererCsrfFilter() {
        RefererCsrfFilter refererCsrfFilter = new RefererCsrfFilter();
        if (StringUtils.isNotEmpty(abSecurityProperties.getCsrfIngores())) {
            refererCsrfFilter.setIngores(Arrays.asList(StringUtils.split(abSecurityProperties.getCsrfIngores(), ",")));
        }

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(refererCsrfFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("refererCsrfFilter");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean requestThreadFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new RequestThreadFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("requestThreadFilter");
        return filterRegistrationBean;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsServiceImpl()).passwordEncoder(new CustomPwdEncoder());
    }
}
