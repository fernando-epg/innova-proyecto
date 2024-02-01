package dev.fernando.proyecto;

import dev.fernando.proyecto.services.ICourseGenericService;
import dev.fernando.proyecto.services.IStudentGenericService;
import dev.fernando.proyecto.services.dto.CourseDTO;
import dev.fernando.proyecto.services.dto.StudentDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@SpringBootApplication
public class ProyectoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =
				SpringApplication.run(ProyectoApplication.class, args);
//		IStudentGenericService userService =applicationContext.getBean(IStudentGenericService.class);
//		userService.save(new StudentDTO("ada","Lovelace",LocalDate.parse("01/06/1984", DateTimeFormatter.ofPattern("MM/dd/yyyy")),"Female","ada@email.com","555555555"));
		ICourseGenericService courseService = applicationContext.getBean(ICourseGenericService.class);
		courseService.save(new CourseDTO("MM-2001","Modelos Matematicos",4.0));
		
		
		System.out.println("Server is running...");
	}

}
