package com.inter.demosca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.inter.demosca", 
	"com.inter.demosca.Controllers", 
	"com.inter.demosca.Entities",
	"com.inter.demosca.Repositories",
	"com.inter.demosca.Services",
	"com.inter.demosca.WebSecurityConfiguration"
})
public class DemoscaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoscaApplication.class, args);
	}

}
