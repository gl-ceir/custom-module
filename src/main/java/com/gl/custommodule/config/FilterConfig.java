package com.gl.custommodule.config;

import com.gl.custommodule.filter.CustomBasicAuthFilter;
import com.gl.custommodule.repository.app.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public Filter customBasicAuthFilter() {
        return new CustomBasicAuthFilter(userRepository);
    }
}

