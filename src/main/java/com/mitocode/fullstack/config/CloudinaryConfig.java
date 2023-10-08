package com.mitocode.fullstack.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dlnwerb6v",
                "api_key", "814866952372284",
                "api_secret", "BOzyo5qiCyr0Y0CoavbZMxoKqlE"));
    }
}
