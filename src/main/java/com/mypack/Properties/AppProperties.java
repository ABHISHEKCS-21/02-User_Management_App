package com.mypack.Properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@EnableAutoConfiguration
@ConfigurationProperties (prefix="app")
@Data
public class AppProperties {
	
	private Map<String,String> messages=new HashMap<String,String>();

}
