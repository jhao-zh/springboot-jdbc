package com.example.JDBC.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
//数据源配置类
public class JDBCConfiguration {
	@Bean
	//连接test库
	public DataSource dataSource() throws Throwable {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		return dataSource;
	}

	@Bean
	@Primary
	//连接test2库并以该数据源为主
	public DataSource dataSource2() throws Throwable {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/test2?serverTimezone=UTC");
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		return dataSource;
	}

	@Bean(name = "jdbctemplate")
	//创建一个连接test库的jdbctemplate bean
	public JdbcTemplate jdbcTemplate() throws Throwable {
		return new JdbcTemplate(dataSource());
	}

	@Bean(name = "jdbctemplate2")
	//创建一个连接test2库的jdbctemplate bean
	public JdbcTemplate jdbcTemplate2() throws Throwable {
		return new JdbcTemplate(dataSource2());
	}
}
