package dev.fernando.proyecto;

import dev.fernando.proyecto.dto.DeliverableDTO;
import dev.fernando.proyecto.dto.EnrolDTO;
import dev.fernando.proyecto.dto.StudentDTO;
import dev.fernando.proyecto.interfaces.ICourseGenericService;
import dev.fernando.proyecto.dto.CourseDTO;
import dev.fernando.proyecto.interfaces.IDeliverableGenericService;
import dev.fernando.proyecto.interfaces.IEnrolGenericService;
import dev.fernando.proyecto.interfaces.IStudentGenericService;
import dev.fernando.proyecto.persistence.EGender;
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
		
		System.out.println("Server is running...");
	}

}
