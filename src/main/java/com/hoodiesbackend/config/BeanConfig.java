package com.hoodiesbackend.config;

import com.hoodiesbackend.utils.encrypting.Encrypting;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfig {

    @Bean
    public Encrypting encrypting(){
        return new Encrypting();
    }
}
