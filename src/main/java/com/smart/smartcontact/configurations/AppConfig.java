package com.smart.smartcontact.configurations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Value("${cloudinary.cloud_name}")
    public String cloudname;
    @Value("${cloudinary.api_key}")
    public String api_key;
    @Value("${cloudinary.api_secret}")
    public String api_secret;
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name",cloudname,
                        "api_key",api_key,
                        "api_secret",api_secret)
        );
    }
}
