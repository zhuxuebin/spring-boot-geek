package com.zxb.spring.transaction.programmatic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @ClassName ProgrammaticTransactionDemoApplication
 * @Description 编程式事务
 * @Author xuery
 * @Date 2019/5/16 9:31
 * @Version 1.0
 */
@SpringBootApplication
@Slf4j
public class ProgrammaticTransactionDemoApplication implements CommandLineRunner {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ProgrammaticTransactionDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("count before transaction:{}", getCount());

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                jdbcTemplate.execute("insert into foo (id,bar) values(1,'aaa')");
                log.info("count in transaction:{}", getCount());
                transactionStatus.setRollbackOnly();//设置回滚标记-让事务在提交阶段不提交
            }
        });
        log.info("count after transaction:{}", getCount());
    }

    private long getCount() {
        return (long) jdbcTemplate.queryForList("select count(*) as cnt from foo").get(0).get("cnt");
    }
}
