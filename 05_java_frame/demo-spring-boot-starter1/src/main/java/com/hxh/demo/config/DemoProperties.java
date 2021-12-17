package com.hxh.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * 配置的属性
 */
@ConfigurationProperties(prefix = "com.hxh")
@Data
public final class DemoProperties {

    private Properties props = new Properties();

}