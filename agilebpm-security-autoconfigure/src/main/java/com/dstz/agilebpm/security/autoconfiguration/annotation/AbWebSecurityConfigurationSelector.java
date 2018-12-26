package com.dstz.agilebpm.security.autoconfiguration.annotation;

import com.dstz.agilebpm.security.autoconfiguration.AbWebHttpSecurityConfiguration;
import com.dstz.agilebpm.security.autoconfiguration.AbWebJwtSecurityConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * ab web安全配置选择器
 *
 * @author wacxhs
 */
public class AbWebSecurityConfigurationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Map<String, Object> attr = annotationMetadata.getAnnotationAttributes(EnableAbWebSecurity.class.getName());
        AbWebSecuritySelector abWebSecuritySelector = (AbWebSecuritySelector) attr.get("value");
        if (AbWebSecuritySelector.HTTP.equals(abWebSecuritySelector)) {
            return new String[]{AbWebHttpSecurityConfiguration.class.getName()};
        } else if (AbWebSecuritySelector.JWT.equals(abWebSecuritySelector)) {
            return new String[]{AbWebJwtSecurityConfiguration.class.getName()};
        }
        return new String[0];
    }
}
