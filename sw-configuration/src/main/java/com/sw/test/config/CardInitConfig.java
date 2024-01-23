package com.sw.test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * This class maintained the system configuration properties.
 * These properties are static and loaded when the server startup.
 * We will make this module as a configuration service, if possible, like spring cloud config.
 */
@Configuration
@ConfigurationProperties(prefix = "cards")
@Data
public class CardInitConfig {


    /**
     *  Initialization of the cards library.
     */
    private List<String> library;
}
