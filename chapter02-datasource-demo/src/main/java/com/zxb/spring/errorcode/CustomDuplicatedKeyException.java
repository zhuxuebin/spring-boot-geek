package com.zxb.spring.errorcode;

import org.springframework.dao.DuplicateKeyException;

/**
 * @ClassName CustomDuplicatedKeyException
 * @Description 自定义异常
 * @Author xuery
 * @Date 2019/5/16 14:20
 * @Version 1.0
 */
public class CustomDuplicatedKeyException extends DuplicateKeyException {

    public CustomDuplicatedKeyException(String msg) {
        super(msg);
    }

    public CustomDuplicatedKeyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
