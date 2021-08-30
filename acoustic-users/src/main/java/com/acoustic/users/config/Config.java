package com.acoustic.users.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Config {
   @LoadBalanced
   @Bean
   public RestTemplate restTemplate() {
      return new RestTemplate();
   }
}