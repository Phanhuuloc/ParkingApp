package com.sungwoo.aps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author phloc
 */
@SpringBootApplication
public class ApsApplication extends SpringBootServletInitializer {
    private final static Logger LOGGER = Logger.getLogger(ApsApplication.class.getName());

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(ApsApplication.class, args);
        LOGGER.info("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            LOGGER.info(beanName);
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApsApplication.class);
    }
}
