package com.algebra.drools.conf;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author MrBird
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:febs-server-system.properties"})
@ConfigurationProperties(prefix = "febs.server.system")
public class FebsServerSystemProperties {
    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;
    /**
     * 批量插入当批次可插入的最大值
     */
    private Integer batchInsertMaxNum = 1000;
    /**
     * swagger配置
     */
    private FesbSwaggerProperties swagger = new FesbSwaggerProperties();
}
