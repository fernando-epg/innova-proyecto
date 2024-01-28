package dev.fernando.proyecto;

import dev.fernando.proyecto.interfaces.IRoomService;
import dev.fernando.proyecto.models.Room;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class ProyectoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =
				SpringApplication.run(ProyectoApplication.class, args);
		IRoomService roomService = applicationContext.getBean(IRoomService.class);
		roomService.save(new Room(101,"Sherlock Holmes",2,0));
		List<Room> rooms = roomService.findAll();
		System.out.println(rooms);
	}

}
