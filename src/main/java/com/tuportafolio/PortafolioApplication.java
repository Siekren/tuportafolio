package com.tuportafolio;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortafolioApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load(); // Cargar variables desde .env
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);
		SpringApplication.run(PortafolioApplication.class, args);
	}

}
