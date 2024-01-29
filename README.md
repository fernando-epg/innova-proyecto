# Proyecto integrador
Este es el proyecto del curso de mid-stack developer de Innova. El objetivo es crear un REST Api que se pueda usar en un
sistema de reservaciones.

Este proyecto se escribió con la intención de usar PostgreSQL, y Spring Boot. También existe la posibilidad de usar un 
servicio No-SQL en el futuro.

## Procedimiento utilizado.
1. Se escribio el modelo a utilizar en el paquete `models`.
2. Todas las interfaces se encuentran en el paquete `interfaces`
3. Los servicios estarán en `services`
4. Los controladores estarán en `controllers`
5. La configuración está en el archivo `application.yml`

## Receta para escribir un proyecto similar
_Se presume que PostgreSQL ya está instalado_
1. Preparar el proyecto en [Spring Initializer](https://start.spring.io/) con las siguientes dependencias:
   * Spring Data JPA
   * PostgreSQL Driver
   * Spring Web
2. Preparar el servidor PostgreSQL
   * Se debe crear la base de datos y se aconseja crear un usuario que actue como _owner_ de la misma.
  
   ```sql
   CREATE USER [user] with password [password]; 
   ALTER USER [user] CREATEDB;
   CREATE DATABASE [database] OWNER [user];
   ```
3. Se debe configurar el proyecto para la conexión a la base de datos, y se recomienda el uso de variables de entorno. Se debe incluir lo siguiente:

   _Se recomienda el refactorizar application.properties a application.yml_
   ```yaml
   server:
     port: [port]
   spring:
     application:
       name: [application_name]
   
     datasource:
       url: ${DB_URL}
       username: ${DB_USER}
       password: ${DB_PASSWORD}
       driver-class-name: org.postgresql.Driver
     
     jpa:
       show-sql: [true/false]
       hibernate:
         ddl-auto: [update/create-drop]
         format-sql: [true/false]
       database: postgresql
       database-platform: org.hibernate.dialect.PostgreSQLDialect
   ```
   
4. Crear el modelo y agregarle las etiquetas para que sea entidad.
   ```java
   @Entity
   public class Model {
   
   @Id
   private Long id_property;
   // ...
   }
   ```
   
5. Crear una interfaz que servira como repositorio y que extienda `JpaRepository<T, ID>`
   ```java
   public interface ModelRepository extends JpaRepository<Model, Long> {
   }
   ```
   
6. Crear una interfaz que sirva para las operaciones CRUD
   ```java
   public interface CrudService<T,ID> {
     T save(T entity);
     Optional<T> findById(ID id);
     List<T> findAll();
     void deleteById(ID id);
     void delete(T entity);
   }
   ```
   
7. Crear una interfaz que sirva para el servicio específico.
   ```java
   public interface Service extends CrudService<Model,Long> {
   // ...
   }
   ```
   
8. Crear el servicio que implemente la interfaz de servicio recien creada. Aquí es donde se debe inyectar el repositorio.

   Aquí tambien se debe implementar los métodos necesarios.
   ```java
   @Service
   public class ModelService implements Service { 
        private final ModelRepository modelRepository;
       
        @Autowired
        public ModelService(ModelRepository modelRepository) {
            this.modelRepository = modelRepository;
        }    
   }
   ```

9. Crear el controlador inyectando el `Service`.
   ```java
   @RestController
   @RequestMapping(/*path*/)
   public class ModelController {
        private final ModelService modelService;
   
        @Autowired
        public ModelController(ModelService modelService) {
            this.modelService = modelService;
        }
        // ...    
   }
   ```
   
10. En la clase de Aplicación (donde esta `main()`), se puede configurar la ejecución como sigue:
    ```java
    public class ModelApplication {
        public static void main(String[] args){
          ConfigurableApplicationContext applicationContext =
				SpringApplication.run(ModelApplication.class, args);
		ModelService modelService = applicationContext.getBean(ModelService.class);
		System.out.println("Server is running...");
        }
    } 
    ```
## Consejos
* Utilizar paquetes para mantener los archivos organizados.
* Las interfaces pueden nombrarse con una i mayúscula antepuesta.
* Se pueden tener métodos que realicen acciones de SQL personalizadas[^referencias]

  [^referencias]:
     Referencias:
     - [Spring JPA @Query example: Custom query in Spring Boot](https://www.bezkoder.com/spring-jpa-query/)
     - [Hibernate - Query Language](https://www.tutorialspoint.com/hibernate/hibernate_query_language.htm)
     - [JPA Query Methods](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html)