package com.matheussantana.cloudnative.temafinal2.config;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean<>(new HystrixMetricsStreamServlet(), "/hystrix.stream");
    }

    @Bean
    public ApplicationInfoManager applicationInfoManager() {
        return new ApplicationInfoManager(new MyDataCenterInstanceConfig(), new ApplicationInfoManager.OptionalArgs());
    }

    @Bean
    public DiscoveryClient discoveryClient() {
        return new DiscoveryClient(applicationInfoManager(), new DefaultEurekaClientConfig());
    }

    @Bean
    public EurekaInstanceConfig eurekaInstanceConfig(){
        return new MyDataCenterInstanceConfig();
    }

    @Bean
    public InstanceInfo instanceInfo() {
        return new EurekaConfigBasedInstanceInfoProvider(eurekaInstanceConfig()).get();
    }

    @Bean
    public DefaultEurekaClientConfig eurekaClientConfig() {
        return new DefaultEurekaClientConfig();
    }
}
