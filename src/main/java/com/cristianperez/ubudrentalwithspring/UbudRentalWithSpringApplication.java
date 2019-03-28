package com.cristianperez.ubudrentalwithspring;

import com.cristianperez.ubudrentalwithspring.presentation.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class UbudRentalWithSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UbudRentalWithSpringApplication.class, args);
	}

	@Autowired
	private Menu menu;

	@Override
	public void run(String... args) throws Exception {

		menu.start();

	}
}
