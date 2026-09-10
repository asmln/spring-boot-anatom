package sag.example.spring_boot_anatom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootAnatomApplication {

	static void main(String[] args) {
		SpringApplication.run(SpringBootAnatomApplication.class, args);
	}

}
