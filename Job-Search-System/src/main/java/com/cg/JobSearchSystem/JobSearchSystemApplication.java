package com.cg.JobSearchSystem;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.function.Predicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication(scanBasePackages = "com.cg")
@EntityScan(basePackages = "com.cg.entity")
@EnableJpaRepositories(basePackages = "com.cg.dao")
@EnableOpenApi
public class JobSearchSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSearchSystemApplication.class, args);
	}
	

	@Bean
	  public Docket openApiEmployeeStore() {
	    return new Docket(DocumentationType.OAS_30)
	        .groupName("open-api-jobsearch-system")
	        .select()
	        .paths(productPaths())
	        .build();
	  }

	  private Predicate<String> productPaths() {
	    return regex(".*/api/.*");
	  }

}
