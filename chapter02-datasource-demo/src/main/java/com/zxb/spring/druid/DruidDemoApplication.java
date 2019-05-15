package com.zxb.spring.druid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

/**
 * @ClassName DruidDemoApplication
 * @Description 使用druid数据连接池
 * 注意点：
 * [1]在spring-boot-starter-jdbc依赖中排除掉默认的HikariCP数据库连接池
 *
 *
 * 实现CommandLineRunner会在应用启动的时候执行对应的run方法
 * @Author xuery
 * @Date 2019/5/15 22:03
 * @Version 1.0
 */
@SpringBootApplication
@Slf4j
public class DruidDemoApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(DruidDemoApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("DruidDemoApplication.dataSource="+dataSource.toString());
    }
}
