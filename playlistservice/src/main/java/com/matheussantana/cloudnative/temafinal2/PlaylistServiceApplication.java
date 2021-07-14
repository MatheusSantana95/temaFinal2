package com.matheussantana.cloudnative.temafinal2;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.matheussantana.cloudnative.temafinal2")
public class PlaylistServiceApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(PlaylistServiceApplication.class, args);
		DiscoveryClient discoveryClient = (DiscoveryClient) applicationContext.getBean("discoveryClient");
		discoveryClient.getApplicationInfoManager().setInstanceStatus(InstanceInfo.InstanceStatus.UP);
	}
}
