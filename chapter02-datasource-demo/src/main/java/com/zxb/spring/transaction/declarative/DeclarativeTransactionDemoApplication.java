package com.zxb.spring.transaction.declarative;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName DeclarativeTransactionDemoApplication
 * @Description 声明式事务（主流）
 * <p>
 * spring boot 可以通过@EnableTransactionManagement注解开启事务注解 @Transactional
 * spring xml 文件可以通过<tx:annotation-driven /> 开启事务注解  tx代表transaction
 * 当然ts中采用统一拦截的方式，对整个类的所有方法都开启事务
 * @Author xuery
 * @Date 2019/5/16 9:42
 * @Version 1.0
 */
@SpringBootApplication
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@Slf4j
public class DeclarativeTransactionDemoApplication implements CommandLineRunner {

    @Autowired
    private FooService fooService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DeclarativeTransactionDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fooService.insertRecord();
        log.info("aaa {}", jdbcTemplate.queryForObject("select count(*) from foo where bar='aaa'", Long.class));

        try {
            fooService.insertThenRollback();
        } catch (Exception e) {
            log.info("bbb {}", jdbcTemplate.queryForObject("select count(*) from foo where bar='bbb'", Long.class));
        }

        try {
            fooService.invokeInsertThenRollback();
        } catch (Exception e) {
            log.info("bbb {}", jdbcTemplate.queryForObject("select count(*) from foo where bar='bbb'", Long.class));
        }

    }
}
