package com.whj.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource(encoding = "UTF-8", value = "classpath:properties/study.properties")
})
public class WhjTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhjTestApplication.class, args);
    }

}
