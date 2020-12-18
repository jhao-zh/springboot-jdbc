package com.example.JDBC.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
public class JDBCConfiguration {
	@Bean
	public DataSource dataSource() throws Throwable {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		return dataSource;
	}

	@Bean
	@Primary
	public DataSource dataSource2() throws Throwable {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/test2?serverTimezone=UTC");
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		return dataSource;
	}

	@Bean(name = "jdbctemplate")
	public JdbcTemplate jdbcTemplate() throws Throwable {
		return new JdbcTemplate(dataSource());
	}

	@Bean(name = "jdbctemplate2")
	public JdbcTemplate jdbcTemplate2() throws Throwable {
		return new JdbcTemplate(dataSource2());
	}
}
