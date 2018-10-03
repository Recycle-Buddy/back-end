package com.recycle.buddy.config;

import com.recycle.buddy.catalog.WasteClassifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecycleBuddyConfiguration {

    @Bean
    public WasteClassifier wasteClassifier() {
        return new WasteClassifier();
    }
}
