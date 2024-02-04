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
		IStudentGenericService studentService =applicationContext.getBean(IStudentGenericService.class);
		ICourseGenericService courseService = applicationContext.getBean(ICourseGenericService.class);
		IEnrolGenericService enrolService = applicationContext.getBean(IEnrolGenericService.class);
//		IDeliverableGenericService deliverableService = applicationContext.getBean(IDeliverableGenericService.class);
		StudentDTO student1 = (StudentDTO) studentService.save(new StudentDTO("ada","Lovelace", LocalDate.parse("01/06/1984", DateTimeFormatter.ofPattern("MM/dd/yyyy")), EGender.FEMALE,"ada@email.com","555555555"));
		studentService.save(new StudentDTO("jack","napier", LocalDate.parse("08/15/1975", DateTimeFormatter.ofPattern("MM/dd/yyyy")),EGender.MALE,"jack@email.com","6666666666"));
		studentService.save(new StudentDTO("luci","morningstar", LocalDate.parse("02/08/1998", DateTimeFormatter.ofPattern("MM/dd/yyyy")),EGender.OTHER,"luci@email.com","1234567890"));
		CourseDTO course1 = (CourseDTO) courseService.save(new CourseDTO("MM-2001","Modelos Matematicos",4.0));
		courseService.save(new CourseDTO("FF-1001","Física 1",3.0));
		courseService.save(new CourseDTO("CS-1001","Introducción a la programación",3.0));
		enrolService.save(new EnrolDTO(String.valueOf(course1.getId()),String.valueOf(student1.getId())));
//		deliverableService.save(new DeliverableDTO(
//				course1.getId(),student1.getId(),100.0,"Deliverable1",10D
//		));*/
		
		System.out.println("Server is running...");
	}

}
