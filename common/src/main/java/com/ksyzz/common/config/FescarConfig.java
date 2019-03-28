package com.ksyzz.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fescar.rm.datasource.DataSourceProxy;
import com.alibaba.fescar.spring.annotation.GlobalTransactionScanner;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置fescar以及数据库代理
 * @author fengqian
 * @since <pre>2019/03/28</pre>
 */
@Configuration
@ConditionalOnClass(GlobalTransactionScanner.class)
@MapperScan("classpath*:/mapper")
public class FescarConfig {
    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 启用fescar的数据库代理
     * @param druidDataSource
     * @return
     */
    @Bean(name = "dataSource")
    DataSourceProxy dataSourceProxy(DruidDataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    /**
     * 配置mybatis的fescar代理
     * @param dataSourceProxy
     * @return
     * @throws Exception
     */
    @Bean
    SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        return sqlSessionFactoryBean.getObject();
    }
    /**
     * init global transaction scanner
     *
     * @Return: GlobalTransactionScanner
     */
    @Bean
    public GlobalTransactionScanner globalTransactionScanner() throws Exception {
        if (applicationName == null) {
            throw new Exception("请配置spring.application.name !");
        }
        return new GlobalTransactionScanner(applicationName, "demo");
    }
}
