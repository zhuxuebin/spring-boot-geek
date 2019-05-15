package com.zxb.spring.druid;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @ClassName ConnectionLogFilter
 * @Description 数据库连接池拦截类，可以继承FilterEventAdapter重写一些方法，在创建连接池前后做一些事
 *
 * [1]注意要在classpath*:META-INF下新建druid-filter.properties并制定Filter的类路径
 *
 * @Author xuery
 * @Date 2019/5/15 22:14
 * @Version 1.0
 */
@Slf4j
public class ConnectionLogFilter extends FilterEventAdapter {

    @Override
    public void connection_connectBefore(FilterChain chain, Properties info) {
        log.info("BEFORE CONNNECTION");
    }

    @Override
    public void connection_connectAfter(ConnectionProxy connection) {
        log.info("AFTER CONNNECTION");
    }
}
