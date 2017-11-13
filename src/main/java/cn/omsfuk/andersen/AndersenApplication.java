package cn.omsfuk.andersen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("cn.omsfuk.andersen.dao")
public class AndersenApplication {

	public static void main(String[] args) {
		SpringApplication.run(AndersenApplication.class, args);
	}
}
