package com.dstz.agilebpm.security.autoconfiguration.annotation;

/**
 * ab web 安全选择器
 *
 * @author wacxhs
 */
public enum AbWebSecuritySelector {

    /**
     * 传统HTTP Session实现方式
     */
    HTTP,

    /**
     * JWT 生成方式
     */
    JWT
}
