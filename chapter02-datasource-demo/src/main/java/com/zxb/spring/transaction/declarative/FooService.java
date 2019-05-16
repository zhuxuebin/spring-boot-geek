package com.zxb.spring.transaction.declarative;

/**
 * @ClassName FooService
 * @Description TODO
 * @Author xuery
 * @Date 2019/5/16 10:03
 * @Version 1.0
 */
public interface FooService {

    void insertRecord();

    void insertThenRollback() throws RollbackException;

    void invokeInsertThenRollback() throws RollbackException;
}
