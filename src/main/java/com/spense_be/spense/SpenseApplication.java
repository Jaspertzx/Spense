package com.spense_be.spense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import com.spense_be.spense.classes.Todo;

import java.util.stream.Stream;

@SpringBootApplication
public class SpenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpenseApplication.class, args);
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> basicsApplicationListener(TodoRepository repository) {
		return event -> repository
				.saveAll(Stream.of("A", "B", "C")
						.map(name -> new Todo("configuration", "congratulations, you have set up correctly!", true))
						.toList())
				.forEach(System.out::println);
	}

}

interface TodoRepository extends CrudRepository<Todo, Long> {

}