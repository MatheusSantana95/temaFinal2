package com.matheussantana.cloudnative.temafinal2;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EntityScan("com.matheussantana.cloudnative.temafinal2")
public class AppServiceApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(AppServiceApplication.class, args);
		DiscoveryClient discoveryClient = (DiscoveryClient) applicationContext.getBean("discoveryClient");
		discoveryClient.getApplicationInfoManager().setInstanceStatus(InstanceInfo.InstanceStatus.UP);
	}
}
