package com.ducatillon.graphqlclient;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class GraphqlClientApplication {

  public static void main(String[] args) throws IOException {
    ConfigurableApplicationContext context = SpringApplication.run(GraphqlClientApplication.class, args);

  }
}
