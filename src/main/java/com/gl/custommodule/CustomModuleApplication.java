package com.gl.custommodule;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEncryptableProperties
@ComponentScan(basePackages = "com.gl.custommodule")
public class CustomModuleApplication {

	public static void main(String[] args){
		SpringApplication.run(CustomModuleApplication.class, args);
	}

}
