package br.com.wildrimak.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SimplificationJwtTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplificationJwtTokenApplication.class, args);
	}
	
	 @Bean
	    public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	    }

}
