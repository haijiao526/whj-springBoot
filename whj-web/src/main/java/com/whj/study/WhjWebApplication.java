package com.whj.study;

import com.whj.study.dao.BaseDaoImpl;
import org.jeesys.common.spring.extra.ClassPathAllPropertySourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.whj"})
@PropertySources({
        @PropertySource(encoding = "UTF-8", value = "classpath:config/jdbc.properties", factory = ClassPathAllPropertySourceFactory.class)
})
@EntityScan(basePackages={"com.whj"})
@EnableJpaAuditing
//扫描定义的Repository
@EnableJpaRepositories(basePackages={"com.whj"},repositoryBaseClass= BaseDaoImpl.class)
public class WhjWebApplication {

    private static final Logger log = LoggerFactory.getLogger(WhjWebApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(WhjWebApplication.class, args);
        log.info("启动成功");
    }

}
