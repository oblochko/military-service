package com.rp.mpgservice.militaryservice.initialazer;

import lombok.experimental.UtilityClass;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@UtilityClass
public class Postgres {

    public final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.1");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasourse.url=" + container.getJdbcUrl(),
                    "spring.datasourse.username=" + container.getUsername(),
                    "spring.datasourse.password=" + container.getPassword()
            ).applyTo(applicationContext);
        }
    }
}
