package com.example.JDBC;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.JDBC.po.User;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
//		DataSourceTransactionManagerAutoConfiguration.class })  //多个数据源需配置
@SpringBootApplication
/**
 ** 实现CommandLineRunner 类 ，需重写 run(String... args)方法
 * */
public class JdbcApplication implements CommandLineRunner {

	@Autowired
	/*
	 * * JdbcTemplate 是Spring对JDBC的封装，目的是使JDBC更加易于使用。 *
	 * 他运行核心的JDBC工作流，如Statement的建立和执行，而我们只需要提供SQL语句和提取结果。
	 */
	// 该程序创建了两个template bean 自动注入会查询到两个，如只创建一个template对象接收会报错
	//用集合可以同时接收两个bean对象
	// JdbcTemplate templates; 再该程序中会报错，除非用@primary注释确认以哪个bean为主
	List<JdbcTemplate> templates;

	// 启动类
	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

	// 查询方法 查询指定template的数据源的数据
	public void sql(int i) {
		String sql = "select * from users";
		//接收查询获得的行数据接收类型，将行数据以User对象的形式存储
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		//list集合接收返回的User对象，query()方法：  sql为查询语句，rowMapper为行数据存储类型
		List<User> users = templates.get(i).query(sql, rowMapper);
		System.out.println(users.toString());
	}

	@Override
	/**
	 ** run(String... args) 该方法是SpringBoot的启动监听方法，该程序初始化后，启动时会调用该方法 
	 * */
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("start >>>>>>>>>>>>>>");
		//随机调用template对象
		Random random = new Random();
		sql(random.nextInt(2));
	}
}
