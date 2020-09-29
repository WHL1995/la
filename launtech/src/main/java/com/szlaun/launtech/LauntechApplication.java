package com.szlaun.launtech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@MapperScan("com.szlaun.launtech.*.*.mapper")
@ComponentScan({"com.szlaun.launtech.*"})
@SpringBootApplication
public class LauntechApplication {

	public static void main(String[] args) {
		SpringApplication.run(LauntechApplication.class, args);
	}

}
