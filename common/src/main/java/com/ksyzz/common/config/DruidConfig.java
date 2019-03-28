package com.ksyzz.common.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置druid
 * @author fengqian
 * @since <pre>2019/03/28</pre>
 */
@Configuration
@ConditionalOnClass(DruidDataSource.class)
public class DruidConfig {

    /**
     * autowired datasource config
     */
    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Value("${druid.username}")
    private String druidUser;

    @Value("${druid.password}")
    private String druidPassword;

    /**
     * 初始化druid datasource
     *
     * @Return: druidDataSource  datasource instance
     */
    @Bean
    @Primary
    public DruidDataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(dataSourceProperties.getUrl());
        druidDataSource.setUsername(dataSourceProperties.getUsername());
        druidDataSource.setPassword(dataSourceProperties.getPassword());
        druidDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        druidDataSource.setInitialSize(5);
        druidDataSource.setMaxActive(180);
        //配置获取连接等待超时的时间
        druidDataSource.setMaxWait(60000);
        druidDataSource.setMinIdle(0);
        druidDataSource.setValidationQuery("Select 1 from DUAL");
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setTestWhileIdle(true);
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        //配置一个连接在池中最小生存的时间，单位是毫秒
        druidDataSource.setMinEvictableIdleTimeMillis(25200000);
        //关闭长时间不使用的连接
        druidDataSource.setRemoveAbandoned(true);
        druidDataSource.setRemoveAbandonedTimeout(1800);
        //关闭abandoned连接时输出错误日志
        druidDataSource.setLogAbandoned(true);
        //打开PSCache，并且指定每个连接上PSCache的大小
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        //启用内置的StartFilter 用于统计监控信息
        StatFilter statFilter = new StatFilter();
        statFilter.setMergeSql(true);
        statFilter.setLogSlowSql(true);
        statFilter.setSlowSqlMillis(3000);
        //启用内置的WallFilter，拦截sql注入
        WallFilter wallFilter = new WallFilter();
        wallFilter.setDbType("mysql");
        //启用日志输出
        Slf4jLogFilter logFilter = new Slf4jLogFilter();
        logFilter.setConnectionLogErrorEnabled(true);
        logFilter.setResultSetLogErrorEnabled(true);
        logFilter.setStatementLogErrorEnabled(true);
        List<Filter> filters = new ArrayList<>();
        filters.add(statFilter);
        filters.add(wallFilter);
        filters.add(logFilter);
        druidDataSource.setProxyFilters(filters);
        return druidDataSource;
    }


    /**
     * 启用druid监控页面
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet(){
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>(
                new StatViewServlet(), "/druid/*");

        servletRegistrationBean.addInitParameter("loginUsername", druidUser);
        servletRegistrationBean.addInitParameter("loginPassword", druidPassword);
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }



}
