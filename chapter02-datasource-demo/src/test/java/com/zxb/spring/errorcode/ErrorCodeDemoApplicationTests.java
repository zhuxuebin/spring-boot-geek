package com.zxb.spring.errorcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName ErrorCodeDemoApplicationTests
 * @Description 错误定制测试类
 * @Author xuery
 * @Date 2019/5/16 14:29
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ErrorCodeDemoApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test(expected = CustomDuplicatedKeyException.class)
    public void testThrowCustomException() {
        jdbcTemplate.execute("insert into foo (id,bar) values(1,'aaa')");
        jdbcTemplate.execute("insert into foo (id,bar) values(1,'bbb')");
    }
}
