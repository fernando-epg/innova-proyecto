package dev.fernando.proyecto;

import dev.fernando.proyecto.interfaces.IRoomService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class ProyectoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =
				SpringApplication.run(ProyectoApplication.class, args);
		IRoomService roomService = applicationContext.getBean(IRoomService.class);
		System.out.println("Server is running...");
	}

}
