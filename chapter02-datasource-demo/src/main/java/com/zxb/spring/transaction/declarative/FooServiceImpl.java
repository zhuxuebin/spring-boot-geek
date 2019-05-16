package com.zxb.spring.transaction.declarative;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName FooServiceImpl
 * @Description TODO
 * @Author xuery
 * @Date 2019/5/16 10:05
 * @Version 1.0
 */
@Component
public class FooServiceImpl implements FooService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FooService fooService;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("insert into foo (bar) values ('aaa')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class) //RollbackException不是RuntimeException的子类，spring不会自动回滚，要指定才会回滚
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("insert into foo (bar) values('bbb')");
        throw new RollbackException();
    }

    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        /**
         * debug发现这里并不会开启事务,这里就要理解proxy：动态代理的原理了
         * Spring会代理FooServiceImpl对象，生成一个代理类FooServiceImplProxy，代理类前后会做一些操作，
         * 然后调用FooServiceImpl本身的方法，如果内部调用this.xxxMethod，则不会重新再做代理了,而是调用对象本身的方法，
         * 相当于只会对最外层做代理，内部通过this.xxxMethod是不会的
         *
         * 关键点：区分代理类和类本身
         */
//        insertThenRollback();


        //todo （1）要想加上事务，解决方法1：注入对象本身，这里fooService其实运行时是调用的代理类FooServiceImplProxy
//        fooService.insertThenRollback();

        //todo (2) 要想加上事务，解决方法2：获取到fooService的代理类FooServiceImplProxy，然后调用方法
        ((FooService)(AopContext.currentProxy())).insertThenRollback();

    }
}
