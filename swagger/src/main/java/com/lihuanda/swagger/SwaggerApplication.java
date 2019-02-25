package com.lihuanda.swagger;

//import com.ly.mssp.client.EnableMSSPClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableMSSPClient
@MapperScan("com.lihuanda.swagger.mapper")
public class SwaggerApplication {

	//classpath 即 WEB-INF 下面的classes文件夹
	public static void main(String[] args) {
		SpringApplication.run(SwaggerApplication.class, args);
	}

}

