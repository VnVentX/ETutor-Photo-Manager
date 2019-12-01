package com.anhtt.imagewarehouse.config;

import org.springframework.boot.web.server.MimeMappings;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * CustomServletConfiguration
 */
@Configuration
public class CustomServletConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        mappings.add("png", "image/png; charset=UTF-8 ");
        factory.setMimeMappings(mappings);
    }

}