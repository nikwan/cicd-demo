package com.acoustic.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(
   basePackages = {"com.acoustic.users"}
)
@EntityScan(
   basePackages = {"com.acoustic.users.entities"}
)
@EnableJpaRepositories(
   basePackages = {"com.acoustic.users.dao"}
)
public class AcousticUsersApplication {
   public static void main(String[] args) {
      SpringApplication.run(AcousticUsersApplication.class, args);
   }
}