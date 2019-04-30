package com.tcore.crudTest.config;
 
 
import com.zaxxer.hikari.HikariDataSource;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
 
import javax.sql.DataSource;
 
@Configuration
public class BeetlSqldbConfig {
    @Bean(name = "dataSource")
    public DataSource dataSource(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return ds;
    }
 
    @Bean
    public BeetlSqlDataSource beetlSqlDataSource(@Qualifier("dataSource") DataSource dataSource) {
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(dataSource);
        return source;
    }
    
    @Bean(name = "sqlManagerFactoryBean")
    @Primary
    public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("dataSource") DataSource datasource) {
        SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(datasource);
        factory.setCs(source);
        factory.setDbStyle(new MySqlStyle());
        factory.setInterceptors(new Interceptor[]{new DebugInterceptor()});
        factory.setNc(new UnderlinedNameConversion());//开启驼峰
        factory.setSqlLoader(new ClasspathLoader("/sql"));//sql文件路径 //默认就是这个路径
        return factory;
    }
    @Bean(name="sqlManager")
    public SQLManager getSqlManager(@Qualifier("sqlManagerFactoryBean") SqlManagerFactoryBean sqlManagerFactoryBean){
        SQLManager sqlManager = null;
        try {
            sqlManager = sqlManagerFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sqlManager;
    }
}
