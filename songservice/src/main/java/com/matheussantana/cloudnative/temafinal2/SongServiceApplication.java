package com.matheussantana.cloudnative.temafinal2;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class SongServiceApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SongServiceApplication.class, args);
		DiscoveryClient discoveryClient = (DiscoveryClient) applicationContext.getBean("discoveryClient");
		discoveryClient.getApplicationInfoManager().setInstanceStatus(InstanceInfo.InstanceStatus.UP);
	}
}
