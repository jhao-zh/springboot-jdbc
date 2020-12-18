package com.example.JDBC;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.JDBC.po.User;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
public class JdbcApplication implements CommandLineRunner {

	@Autowired
	List<JdbcTemplate> templates;

	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

	public void sql(int i) {
		String sql = "select * from users";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		List<User> users = templates.get(i).query(sql, rowMapper);
		System.out.println(users.toString());
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("start >>>>>>>>>>>>>>");
		Random random = new Random(); 
		sql(random.nextInt(2));
	}
}
