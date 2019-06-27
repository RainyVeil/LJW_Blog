package com.ljw.springboot.thymeleaf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement//开启事务支持
@SpringBootApplication
@EnableAutoConfiguration
//改变自动扫描的包
@ComponentScan(basePackages = {"com.ljw.springboot.thymeleaf"})
@MapperScan(basePackages = {"com.ljw.springboot.thymeleaf.mapper"})
public class ThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafApplication.class, args);

		}
		}
