package com.dstz.agilebpm.security.autoconfiguration.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用ab web安全
 *
 * @author wacxhs
 */
@Import(AbWebSecurityConfigurationSelector.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableAbWebSecurity {

    AbWebSecuritySelector value() default AbWebSecuritySelector.HTTP;

}
