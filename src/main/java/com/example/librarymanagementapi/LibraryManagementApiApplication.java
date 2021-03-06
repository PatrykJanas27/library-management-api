package com.example.librarymanagementapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class LibraryManagementApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LibraryManagementApiApplication.class, args);
        context.getBean(InitialValuesSeed.class).fillCategoryTableInDatabase();
        context.getBean(InitialValuesSeed.class).fillAuthorTableInDatabase();
    }

}
