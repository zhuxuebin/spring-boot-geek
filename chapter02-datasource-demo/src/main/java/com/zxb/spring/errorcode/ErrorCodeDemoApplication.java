package com.zxb.spring.errorcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName ErrorCodeDemoApplication
 * @Description 自定义数据库抛出的异常
 *
 * Spring其实就是根据每种数据库的错误码，采用org.springframework.jdbc.support/sql-error-codes.xml配置定制每种错误码的异常
 *
 *  如果想自己定制异常，则可以将sql-error-codes.xml文件拷贝到resources目录下并做修改，Spring会优先选择SQLErrorCodes.customTranslations
 *
 * @Author xuery
 * @Date 2019/5/16 14:17
 * @Version 1.0
 */
@SpringBootApplication
public class ErrorCodeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrorCodeDemoApplication.class,args);
    }
}
