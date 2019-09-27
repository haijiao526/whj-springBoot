package com.whj.study;

import com.whj.study.dao.BaseDaoImpl;
import org.jeesys.common.spring.extra.ClassPathAllPropertySourceFactory;
import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.whj"})
@PropertySources({
        @PropertySource(encoding = "UTF-8", value = "classpath*:config/jdbc.properties", factory = ClassPathAllPropertySourceFactory.class)
})
@EntityScan(basePackages={"com.whj"})
@EnableJpaAuditing
//扫描定义的Repository
@EnableJpaRepositories(basePackages={"com.whj"},repositoryBaseClass= BaseDaoImpl.class)
public class WhjWebApplicationTests {

    @Test
    public void contextLoads() {
    }

}
