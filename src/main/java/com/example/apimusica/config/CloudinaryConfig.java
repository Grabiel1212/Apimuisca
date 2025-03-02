package com.example.apimusica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "ddhvekdas",
                "api_key", "287518215646346",
                "api_secret", "pSq8KQDEfvMMygX_pR22L-wT8SM",
                "secure", true));
    }
}