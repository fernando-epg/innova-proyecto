_[Spanish version further down](#proyecto-integrador)_

# Captstone project

This is a project for the mid-stack developer course from Innova. The goal is to create a REST API that could be used on a booking system.

This project was coded with the intent of using PostgreSQL and Spring Boot. Also, there's the posibility of using a Non-SQL service in the future.

## Used procedure

1. The model was written on the `models` package.
2. All of the interfaces are on the `interfaces` package.
3. The services will be on `services`.
4. The controllers will be on `controllers`.
5. The configuration will be on the `application.yml` file.

## Recipe for a similar project

   _We will be assuming that PostgreSQL is already installed and configured on the system._

1. Prepare the project on [Spring Initializer](https://start.spring.io/) with the following dependencies:
   * Spring data JPA
   * PostgreSQL Driver
   * Spring Web

2. Prepare the PostgreSQL server
   * The database needs to be created and it's advised create a user that acts as _owner_.

   ```sql
   CREATE USER [user] with password [password];
   ALTER USER [user] CREATEDB;
   CREATE DATABASE [database] OWNER [user];
   ```

3. The project needs to be configured with the connection for the database, and it's recommended the use of system variables. The following needs to be included:

   _It's advised to refactor `application.properties` to `application.yml`_

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
        properties:
          hibernate:
            format_sql: [true/false]
        database: postgresql
        database-platform: org.hibernate.dialect.PostgreSQLDialect
    ```

4. Create the model and add the tags for it to be considered as entity.

   ```java
    @Entity
    public class Model {

        @Id
        private Long id_property;
        // ...
    }
    ```

5. Create an interface that will work as a repository and it needs to extend `JpaRepository<T, ID>`

   ```java
    public interface ModelRepository extends JpaRepository<Model, Long> {
    }
    ```

6. Create an interface that will be used for CRUD operations

   ```java
    public interface CrudService<T,ID> {
        T save(T entity);
        Optional<T> findById(ID id);
        List<T> findAll();
        void deleteById(ID id);
        void delete(T entity);
    }
    ```

7. Create an interface to be used with the specific service.

   ```java
    public interface Service extends CrudService<Model,Long> {
        // ...
    }
    ```

8. Create a service that implements the service interface that we just created. This is where we need to inject the repository and it needs to implement the necesary methods.

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

9. Create the controller injecting the `Service`.

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

10. In the Application class (where `main()` is located), you can configure the execution as follows:

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

## Tips

* Use packages to keep the files organized.
* The interfaces can be named with an uppercase I at the beginning.
* You can have methods that perform customized SQL actons. [^referencias]

## JWT

The use of JWT is to provide robustness and to the project. This is done using the JSON Web Token. As a reference, you can use the video found in <https://www.youtube.com/watch?v=KxqlJblhzfI>. The following steps are the way it was implemented based on this video.

   _The dependency version used on the project is 0.11.5_

### Dependencies

You must add the following dependencies on the Spring Initializer. If these haven't been addedpreviously, they can be found in <https://mvnrepository.com>.
The dependencies must be added depending the intended use, and the ones shown here are the minimum ones:

* Spring Web
* Spring Security
* Spring Data JPA
* (Database drive that we'll be using)
* Lombok (recommended, albeit not required)

## Steps

### Preparation

1. You must configure the datasource (this has already been done previously).
2. You ust create a new database for the project (this has also been done on previous steps)
3. You must establish a connection with the database from `application.yml`
4. The class that would be used to authenticate (for example `User`). It's advised to have it organized whilst the structure creation.
   * It's advised the use of **Lombok** annotations `@Data, @Builder, @NoArgsConstructor`.
5. The Entity needs to be defined based on the recently created class with `@Entity`. If you need to change the name of the table, you would use the tag `@Table(name="[table_name]")`. Also you can add the tag within `@Table` to define that a particular column should have a unique value such as `@Table(name="_user", uniqueConstraints = {@UniqueConstrait(columnNames={"email"})})`.
6. The Primary Key should be defined with `@Id`.
   * If needed, you will need to establis the sequence and value that needs to be used. You could use the following:

    ```java
    @SequenceGenerator(
        name = "user_id_sequence",
        sequenceName = "user_id_sequence"
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_id_sequence"
    )
    ```

7. With all this already done, you can run the project with the intent of checking that the tables are created in the database.

### `UserDetails` interface implementation

You will need to implement `UserDetails` on the created class. You need to implement the methods as needed.

8. `getAuthorities()` implementation:

* First, you will need to create something that shows the roles for the user. For this, it's recommended an `enum` and needs to be added to the class from step 4. It's recommended the use of the tag `@Enumerated(EnumType.STRING)` on this `enum`.
* The implementation could be:

    ```java
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    ```

9. `getUserName()` implementation:

* If you would be used the email as user, you could return it

    ```java
    @Override
    public String getUsername() {
        return email;
    }
    ```

10. `isAccountNonExpired()` implementation:

* You must ensure that this is `true`.

    ```java
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    ```

11. `isAccountNonLocked()` implementation:

* Must be `true` as a result

    ```java
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    ```

12. `isCredentialsNonExpired()` implementation:

* Must return `true` as a result.

    ```java
    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }
    ```

13. `isEnabled()` implementation:

* Must return `true` as a result.

    ```java
    @Override
    public boolean isEnabled() {
       return true;
    }
    ```

14. If due to using Lombok there's no getter for `password`, it's **strongly suggested** that you include the password getter explicitly.

    ```java
    @Override
    public String getPassword() {
       return password;
    }
    ```

#### Repository creation

15. A repository needs to be created for the class that was created in step 4. This needs to extend the repository type that will need to be used (`JpaRepository`, `MongoRepository`, etc.).

    ```java
    public interface [nombre_repositorio] extends JpaRepository<T,ID> {
    }
    ```

16. Within the repository, you must add a new method to find the user with the email, this is assuming that the email is the unique attribute.

    ```java
    Optional<User> findByEmail(String email);
    ```

#### JWT Filter creation

17. Now we would need to create the configuration for the JWT filter. It's advisable doing it within the `config` package. The configuration must be a class, that needs to extend `OncePerRequestFilter` as follows:

    ```java
    public class JwtAuthenticationFilter extends OncePerRequestFilter {
    }
    ```

    This forces the `OncePerRequestFilter` methods, which leaves us with:

    ```java
    import org.springframework.lang.NonNull;

    public class JwtAuthenticationFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
        ) throws ServletException, IOException {

        }
    }
    ```

   From this, we would need to notice the following:

* With `request` we can catch all the operations for the HTTP request.
* With `response` we can catch all the replies we provide.
* `filterChain` is the list of filters that needs to be applied on the HTTP operation.

18. As a final step, we need to use the Spring tag `@Component` and Lombok tag `@RequiredArgsConstructor`.

#### JWT Token extraction

19. First, we will need to retrieve the header from the `request`.

    ```java
    final String authHeader = request.getHeader("Authorization");
    ```

20. Then, we need to confirm that the header is the one that's expected. At the same time, we will store the JWT Token with its variable. If it can't be found, then it means that it needs to conitnue with the next filter on the chain.

    ```java
    final String jwt;
    if(authHeader == null || !authHeader.startsWith("Bearer ")) {
       filterChain.doFilter(request,response);
       return;
    }
    ```

21. If this verification is passed, then the token must be extracted:

    ```java
    jwt = authHeader.substring(7);
    ```

  The value is 7, as `"Bearer "` is 7 characters long.

***

_The `JwtAuthenticationFilter` class should look as follows so far:_

```java
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
    }
}
```

***

#### Confirming the existence of user and password

After extracting the JWT token, we must call `UserDetailService` to confirm that the user already exists. For this, we need to use a `JWTService` to extract the username. If we're using the email, this is what will be used as user.

22. We'll extract the user from `request`, but for this we'll use another class of type `JwtService`. For the time being, we'll leave it asa TODO.

    ```java
    final String userEmail;

    if(authHeader == null || !authHeader.startsWith("Bearer ")) {
       filterChain.doFilter(request,response);
       return;
    }
    jwt = authHeader.substring(7);
    userEmail = //TODO extract userEmail from JWT Token
    ```

23. In the class, befor the filter, we will create a `JwtService` element. We'll be using this to extract the user.

    ```java
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
    // ...
    jwt = authHeader.substring(7);
    userEmail = jwtService.extractUsername(jwt);//TODO extract userEmail from JWT Token
    ```

24. It's at this point where we create the `JwtService` class (or record). Within this class, we create the method `extractUsername()`.

    ```java
    @Service
    public class JwtService {
       public String extractUsername(String token) {
           return null; // For the moment.
       }
    }
    ```

#### Adding JWT dependencies

25. To manage the JWT tokens we need to add some dependencies on `pom.xml`. After adding these dependencies, we can continue on `JwtService`:

    ```xml
    <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>

    <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>
    ```

#### Extracting "claims"

26. To ease the operation, you can extract all claims with a private method on `JwtService`:

    ```java
    private Claims extractAllClaims(String token) {
         return Jwts
                 .parserBuilder()
                 .setSigningKey(getSignInKey())
                 .build()
                 .parseClaimsJws(token)
                 .getBody();
    }
    ```

#### Use of the SignInKey

The SignInKey is used to ensure that the client is whoever it claims to be.

27. You need to create a secret 256-bit minimum key. For this, you can use online tools, such as <https://www.devglan.com/online-tools/hmac-sha256-online>.

28. Once the key has been created, you can use it as an environment value and add it to `application.yml` as follows:

    ```yml
    application:
      security:
        jwt:
          secret_key: ${JWT_SECRET_KEY}
          expiration: 86400000 #1 dia
    ```

29. Then, on `JwtService` you can add the key with:

    ```java
    @Value("${application.security.jwt.secret_key}")
    private static String SECRET_KEY;

    @Value("${application.security.jwt.expiration}")
    private static Long EXPIRATION;
    ```

30. With the key already added, we can implement the `getSignInKey()` method that was pending.

    ```java
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    ```

#### Single claim extraction

31. With all prior steps ready, we can extract a unique claim as follows:

    ```java
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    ```

#### Username extraction

In step 24, we had a pending process to extract the username. It's time to implement it.

32. To extract the username, we need to do it as follows:

    ```java
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    ```

#### JWT generation

We're implementing now the JWT generation.

33. The JWT token can be created with:

    ```java
    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(LocalDateTime.now()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setExpiration(Date.from(LocalDateTime.now()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                        .plusMillis(EXPIRATION)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
        }
    ```

    If you need a token without `extraClaims` you would need something along the following:

    ```java
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    ```

Both methods, depending on the use, can be `private`, and just use another method not to expose the method directly.

#### Token validation

34. A token can be validated as follows:

    ```java
    public boolean isTokenValid(String token, UserDetails userDetails) {
       final String username = extractUsername(token);
       return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    ```

35. To verify if the token is expired, we'll be using 2 methods, `isTokenExpired()` y `extractExpiration()`:

    ```java
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).isBefore(LocalDateTime.now());
    }

    private LocalDateTime extractExpiration(String token) {
    Date date = extractClaim(token, Claims::getExpiration);
    return date.toInstant()
               .atZone(ZoneId.systemDefault())
               .toLocalDateTime();
    }
    ```

***
_The service should be as follows:_

```java
@Service
public class JwtService {
    @Value("${application.security.jwt.secret_key}")
    private static String SECRET_KEY;

    @Value("${application.security.jwt.expiration}")
    private static Long EXPIRATION;

    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(LocalDateTime.now()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setExpiration(Date.from(LocalDateTime.now()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                        .plusMillis(EXPIRATION)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).isBefore(LocalDateTime.now());
    }

    private LocalDateTime extractExpiration(String token) {
        Date date = extractClaim(token, Claims::getExpiration);
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
```

#### Finishing the validation process

Now we can go back to `JwtAuthenticationFilter` and finish the validation process.

36. First, we must confirm if the user has been validated previously. This is done for us not needing to validate every single time to the identity database. For this, we will need to create a `UserDetailService` element, which will be a self implementation.

    ```java
    private final UserDetailsService userDetailsService;

    // ...

    jwt = authHeader.substring(7);
    userEmail = jwtService.extractUsername(jwt);
    if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
           UserDetails userDetails = this.userDetailsService.loadByUsername(userEmail);
    }
    ```

37. Now, we must create a class that will work to configure some injections. We'll create a class on the `config` package called `config` llamada `ApplicationConfiguration`.

    We must add the `@Configuration` and `@RequiredArgsConstructor` tags:

    ```java
    @Configuration
    @RequiredArgsConstructor
    public class ApplicationConfig {

    }
    ```

38. We'll need to create a `@Bean` now for the service that requires `UserDetailsService`

    ```java
    private final IUserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
    return username -> userRepository.findByEmail(username)
               .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    ```

39. With the `@Bean` created, we need the requirement that we had pending on `JwtAuthenticationFilter`. Now, we can finish validating the user as follows:

    ```java
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
          if(jwtService.isTokenValid(jwt,userDetails)) {
              UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                      userDetails,
                     null,
                      userDetails.getAuthorities()
              );
              authToken.setDetails(
                     new WebAuthenticationDetailsSource().buildDetails(request)
              );
              SecurityContextHolder.getContext().setAuthentication(authToken);
          }
    }
    ```

With this, the filter is already configured, and we can just pass it on to the next filter.
***
_The class should look as follows:_

```java
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwt,userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
```

#### SecurityConfig

40. Even with all the work that has been done, we're still missing a configuration that would allow the operations to be triggered at the right moment. For this, we'll create a new class called `SecurityConfiguration`.

    ```java
    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
        public class SecurityConfiguration {
    }
    ```

   **The `@Configuration` and `@EnableWebSecurity` tags should always be together.**

41. Before continuing, we must define if we're gonna be using a list of endpoints for which we won't be requiring authentication (such as if the user is registering). This can be done first by defining a list of the endpoints as:

    ```java
    private static final String[] WHITE_LIST_URL = {
            "/api/v1/health", 
            "/api/v1/auth/**"
    };
    ```

42. We would also require to create an associtation for the use on `JwtAuthenticationFilter`:

    ```java
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    ```

43. Last, we need to add an authentication provider. This will have to be of `AuthenticationProvider` type, which would be an `ApplicationConfig` Bean:

    ```java
    private final AuthenticationProvider authenticationProvider;
    ```

#### Back to `ApplicationConfig`

44. On `ApplicationConfig` we would need to create a Bean that handles the authentication, we'll be using a DAO type object.

    ```java
    @Bean
    public AuthenticationProvider authenticationProvider() {
       DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
       return authProvider;
    }
    ```

   We must remember that the `UserDetailsService` that's being used was defined in step 38.

45. We will need to also add an encoder for the passwords. This can be done with the `passwordEncoder()` Bean, that can be as follows:

    ```java
    @Bean
    private PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
    }
    ```

46. Last, we need to create a Bean of `AuthenticationManager` type as follows:

    ```java
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
    ```

#### Authentication controller

47. We will need to configure 2 endpoints that will be used for the authentication tasks (register and logging in). For this, well be using a controller called `AuthenticationController`:

    ```java
    @RestController
    @RequestMapping("/api/v1/auth")
    @RequiredArgsConstructor
    public class AuthenticationController {
    }
    ```

48. We create 2 endpoints, one for the registry and one for the authentication:

    ```java
    @PostMapping("/register")
    public ResponseEntity<AutheticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        //
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AutenticationResponse> register(
           @RequestBody AuthenticationRequest request
    ) {
        //
    }
    ```

49. We'll need to implement `AuthenticationResponse`, `RegisterRequest`, and `AuthenticationRequest`
    1. We need to create the class `AuthenticationResponse` that can be as follows:

    ```java
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class AutheticationResponse {
        private String token;
    }
    ```

    2. The `RegisterRequest` class, that can be understood as a DTO, needs to be created and could be as follows. _This element can be a **record**_:

    ```java
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class RegisterRequest {
        private String firstName;
        private String lastName;
        private String email;
        private String password;
    }
    ```

    3. The `AuthenticationRequest` class can also be a DTO, and could be implemented as follows:

    ```java
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class AuthenticationRequest {
        private String email;
        private String password;
    }
    ```

***
_The `AuthenticationController` controlles should look like this:_

```java
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
```

***

#### Authentication service

50. A new service needs to be created and will handle the authentication operations. This service can be on the `AuthenticationService` class. In the class the `@Service` adn `@RequiredArgsConstructor` tags must be added:

    ```java
    @Service
    @RequiredArgsConstructor
    public class AuthenticationService {
    }
    ```

51. With this last service, we can proceed to inject to `AuthenticationController` and use it to return the necessary objects:

    ```java
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    ```

    Note that the `register()` and `authenticate()` methodds need to be implemented on the servie.

52. For the `register()` method we will need to inject the repository (step 15), the password encoder (step 45) and the JWT service (steps 29-35) and can be implemented as follows:

    ```java
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ERole.STUDENT)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    ```

53. For `authenticate()` you can use the `AuthenticationManager`Bean (step 46), which needs to be injected and can be implemented as follows:

    ```java
    private final AuthenticationManager authenticationManager;
    // ...
    
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    ```

***
_The `AuthenticationService` class should look like this:_

```java
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ERole.STUDENT)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
```

***

### Use

With all the configuration loaded, we can use a client like Postman to perform HTTP operations. We need to consider that on the header of the requests **that are not for new user** they will need to be authenticated with the value recieved after the successful log in. The tipe of token to be used should be _Bearer_.

### Other resources

* <www.youtube.com/watch?v=jQrExUrNbQE> - Authentication based on roles
* <www.youtube.com/watch?v=RnZmeczS_DI> - Use of new versions of JWT

***

# Proyecto integrador

Este es el proyecto del curso de mid-stack developer de Innova. El objetivo es crear un REST API que se pueda usar en un sistema de reservaciones.

Este proyecto se escribió con la intención de usar PostgreSQL, y Spring Boot. También existe la posibilidad de usar un servicio No-SQL en el futuro.

## Procedimiento utilizado

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

   _Se recomienda el refactorizar `application.properties` a `application.yml`_

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
        properties:
          hibernate:
            format_sql: [true/false]
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

8. Crear el servicio que implemente la interfaz de servicio recien creada. Aquí es donde se debe inyectar el repositorio y se debe implementar los métodos necesarios.

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
* Se pueden tener métodos que realicen acciones de SQL personalizadas [^referencias]

[^referencias]: References / Referencias:
  [Spring JPA @Query example: Custom query in Spring Boot](https://www.bezkoder.com/spring-jpa-query/) /
  [Hibernate - Query Language](https://www.tutorialspoint.com/hibernate/hibernate_query_language.htm) /
  [JPA Query Methods](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html)

***

## JWT

El uso de JWT es para darle robustez y seguridad al proyecto. Está basado en el uso del JSON Web Token. Como referencia, se puede utilizar el video encontrado en <https://www.youtube.com/watch?v=KxqlJblhzfI>. El orden que se muestra a continuación, es la manera como se implementó basado en este último video.

   _La versión de dependencias que se usa en el proyecto es 0.11.5_

### Dependencias

Se deben agregar las siguientes dependencias en el Spring Initializer. Si no están, se encuentran en <https://mvnrepository.com>.
Las dependencias se deben agregar dependiendo del uso, y las siguientes son las mínimas.

* Spring Web
* Spring Security
* Spring Data JPA
* (Driver de la base de datos que se usará)
* Lombok (recomendado)

### Pasos

#### Preparación

1. Se configura el datasource (Esto ya está realizado en las secciones anteriores).
2. Se debe crear una nueva base de datos para el proyecto (ya se realizó en los pasos anteriores).
3. Se establece la conexión con la base de datos a partir de `application.yml`.
4. Se crea la clase que servirá para autenticar (por ejemplo `User`). Se recomienda tener orden en la creación de la estructura.
   * Se recomienda el uso de las anotaciones de **Lombok** `@Data, @Builder, @NoArgsConstructor`.
5. Se define la Entidad a partir de la clase recién creada con `@Entity`. Si se debe cambiar el nombre de la tabla, se usa la anotación `@Table(name="[nombre_tabla]")`. También se puede agregar la anotación dentro de `@Table` para definir que una columna deba tener un valor único como por ejemplo `@Table(name="_user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})`.
6. Se define cuál será el dato que sirva como llave primaria con `@Id`.
   * De ser necesario, se debe establecer la secuencia y el valor que deberá usarse. Para esto se puede hacer con lo siguiente:

    ```java
    @SequenceGenerator(
        name = "user_id_sequence",
        sequenceName = "user_id_sequence"
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_id_sequence"
    )
    ```

7. Con esto ya realizado, se puede correr el proyecto con la intención de verificar que se creen las tablas necesarias en la base de datos.

#### Implementación de interfaz `UserDetails`

Se debe implementar `UserDetails` en la clase creada. Se deben implementar los métodos de manera como se necesiten.

8. Implementación de `getAuthorities()`:

* Se debe crear primero algo que muestre los roles del usuario. Para esto se recomienda un `enum` y se debe agregar a la clase del paso 4. Se recomienda usar la anotación `@Enumerated(EnumType.STRING)` en este `enum`.
* La implementación puede ser:

    ```java
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    ```

9. Implementación de `getUserName()`:

* Si se va a usar el email como usuario, se puede retornar el mismo

    ```java
    @Override
    public String getUsername() {
        return email;
    }
    ```

10. Implementación de `isAccountNonExpired()`:

* Se debe asegurar que sea `true`.

    ```java
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    ```

11. Implementación de `isAccountNonLocked()`:

* Debe ser `true` como resultado

    ```java
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    ```

12. Implementación de `isCredentialsNonExpired()`:

* Debe devolver `true` como resultado.

    ```java
    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }
    ```

13. Implementación de `isEnabled()`:

* Debe retornar `true` como resultado.

    ```java
    @Override
    public boolean isEnabled() {
       return true;
    }
    ```

14. Si por el uso de Lombok no hay un getter para `password`, es **sumamente recomendable** que se haga un explicito del
getter de `password`

    ```java
    @Override
    public String getPassword() {
       return password;
    }
    ```

#### Creación del repositorio

15. Se debe crear un repositorio para la clase que se creó en el paso 4. El mismo debe extender el tipo de repository
que se utilizará (JpaRepository, MongoRepository, etc.)

    ```java
    public interface [nombre_repositorio] extends JpaRepository<T,ID> {
    }
    ```

16. Dentro del repositorio, se deberá agregar un nuevo método para encontrar el usuario por correo. Esto es asumiendo
que el correo es un atributo único.

    ```java
    Optional<User> findByEmail(String email);
    ```

#### Creación de JWT Filter

17. Ahora tendremos que crear la configuración para el filtro de JWT. Es recomendable hacerlo dentro de un paquete
`config`. La configuración debe ser una clase, que tiene que extender `OncePerRequestFilter` como sigue:

    ```java
    public class JwtAuthenticationFilter extends OncePerRequestFilter {
    }
    ```

    Esto obliga a implementar los métodos de `OncePerRequestFilter` lo cual nos deja con lo siguiente:

    ```java
    import org.springframework.lang.NonNull;

    public class JwtAuthenticationFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
        ) throws ServletException, IOException {

        }
    }
    ```

  De esto, hay que hacer notar lo siguiente:

* Con `request` podemos interceptar todos las operaciónes de request del HTTP.
* Con `response` podemos interceptar todas las respuestas que brindamos.
* `filterChain` es el listado de filtros que se deberan aplicar en la operación HTTP.

18. Como paso final, hay que usar las anotaciones `@Component` de Spring y `@RequiredArgsContructor` de Lombok.

#### Extracción del Token JWT

19. Lo primero que debemos hacer es recuperar el header del `request`.

    ```java
    final String authHeader = request.getHeader("Authorization");
    ```

20. Luego, debemos confirmar que el header es el que se espera. Al mismo tiempo, guardamos el token JWT en su variable. Si no se encuentra, entonces quiere decir que se debe continuar con el siguiente filtro en la cadena.

    ```java
    final String jwt;
    if(authHeader == null || !authHeader.startsWith("Bearer ")) {
       filterChain.doFilter(request,response);
       return;
    }
    ```

21. Si pasamos esta verificación, entonces el token debe ser extraido:

    ```java
    jwt = authHeader.substring(7);
    ```

  El valor es 7 porque `"Bearer "` tiene 7 caracteres.

***
_La clase `JwtAuthenticationFilter` hasta ahora deberá parecer a lo siguiente:_

```java
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
    }
}
```

***

#### Confirmación que existe el usuario y contraseña

Después de extraer el token JWT, debemos llamar a `UserDetailService` para confirmar si el usuario ya existe. Para esto, debemos usar un `JWTService` para extraer el usuario. Si se está usando el correo, esto es lo que se usará como usuario.

22. Extraemos el usuario del `request`, pero para esto vamos a usar otra clase de tipo `JwtService`. Por el momento, lo
dejaremos como TODO.

    ```java
    final String userEmail;
    
    if(authHeader == null || !authHeader.startsWith("Bearer ")) {
       filterChain.doFilter(request,response);
       return;
    }
    jwt = authHeader.substring(7);
    userEmail = //TODO extract userEmail from JWT Token
    ```

23. En la clase, antes del filtrado, crearemos un elemento del tipo `JwtService`. Usaremos esto para extraer el usuario

    ```java
    private final JwtService jwtService;
    
    @Override
    protected void doFilterInternal(
    // ...
    jwt = authHeader.substring(7);
    userEmail = jwtService.extractUsername(jwt);//TODO extract userEmail from JWT Token
    ```

24. Es en este punto donde creamos la clase (o record) `JwtService`. Dentro de este elemento, creamos el método
`extractUsername()`

    ```java
    @Service
    public class JwtService {
       public String extractUsername(String token) {
           return null; // For the moment.
       }
    }
    ```

#### Agregar dependencias de JWT

25. Para manejar los tokens de JWT se nececita agregar unas dependencias en el `pom.xml`. Con las dependencias
agregadas, ya podemos continuar en `JwtService`:

    ```xml
    <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>

    <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>
    ```

#### Extrayendo "claims"

26. Para facilitar la operación, se puede extraer todos los claims con un método privado en `JwtService`:

    ```java
    private Claims extractAllClaims(String token) {
         return Jwts
                 .parserBuilder()
                 .setSigningKey(getSignInKey())
                 .build()
                 .parseClaimsJws(token)
                 .getBody();
    }
    ```

#### Uso de la llave de firmado (SignInKey)

La llave de SignInKey sirve para asegurar que el cliente es quien dice ser.
27. Se debe crear una llave secreta de al menos 256 bits. Para esto se pueden usar herramientas en línea. Una herramienta que puede usarse es <https://www.devglan.com/online-tools/hmac-sha256-online>

28. Una vez que se ha creado la llave, se puede usar como variable de entorno y se puede hacer el uso desde el `application.yml` agregando lo siguiente

    ```yml
    application:
      security:
        jwt:
          secret_key: ${JWT_SECRET_KEY}
          expiration: 86400000 #1 dia
    ```

29. Luego en `JwtService`, se puede agregar la llave con:

    ```java
    @Value("${application.security.jwt.secret_key}")
    private static String SECRET_KEY;

    @Value("${application.security.jwt.expiration}")
    private static Long EXPIRATION;
    ```

30. Ya con la llave agregada, se puede implementar el método `getSignInKey()` que tenemos pendiente.

    ```java
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    ```

#### Extracción de solo 1 claim

31. Con lo anterior listo, podemos extraer un claim único con lo siguiente:

    ```java
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    ```

#### Extracción del username

En el paso 24, dejamos pendiente el proceso de extracción de usuario. Es momento de implementarlo.

32. Para extraer el usuario, se hace lo siguiente:

    ```java
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    ```

#### Generación de JWT

Vamos a implementar la generación de un token JWT.

33. El token JWT se puede crear con:

    ```java
    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(LocalDateTime.now()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setExpiration(Date.from(LocalDateTime.now()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                        .plusMillis(EXPIRATION)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
        }
    ```

    Si se necesita un token sin `extraClaims`, esto se puede hacer como sigue:

    ```java
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    ```

Ambos métodos, de ser necesario, pueden ser `private`, y solo se usa otro método para no exponer el método de forma directa.

#### Validación de token

34. Se puede validar el token de la siguiente manera:

    ```java
    public boolean isTokenValid(String token, UserDetails userDetails) {
       final String username = extractUsername(token);
       return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    ```

35. Para verificar si el token está expirado, utilizamos 2 métodos, `isTokenExpired()` y `extractExpiration()`:

    ```java
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).isBefore(LocalDateTime.now());
    }

    private LocalDateTime extractExpiration(String token) {
    Date date = extractClaim(token, Claims::getExpiration);
    return date.toInstant()
               .atZone(ZoneId.systemDefault())
               .toLocalDateTime();
    }
    ```

***
_El servicio debería estar como sigue:_

```java
@Service
public class JwtService {
    @Value("${application.security.jwt.secret_key}")
    private static String SECRET_KEY;

    @Value("${application.security.jwt.expiration}")
    private static Long EXPIRATION;

    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(LocalDateTime.now()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .setExpiration(Date.from(LocalDateTime.now()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                        .plusMillis(EXPIRATION)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).isBefore(LocalDateTime.now());
    }

    private LocalDateTime extractExpiration(String token) {
        Date date = extractClaim(token, Claims::getExpiration);
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
```

***

#### Terminando el proceso de validación

Ahora podemos regresar a `JwtAuthenticationFilter` y terminar los procesos de validación.

36. Primero debemos confirmar si el usuario ya ha sido validado previamente. Esto es para no estar validando hacia la base de datos de identidad con cada operación. Para esto, tendremos también que crear un elemento del tipo `UserDetailService`, el cual será una implementación propia.

    ```java
    private final UserDetailsService userDetailsService;

    // ...

    jwt = authHeader.substring(7);
    userEmail = jwtService.extractUsername(jwt);
    if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
           UserDetails userDetails = this.userDetailsService.loadByUsername(userEmail);
    }
    ```

37. Ahora, debemos hacer una clase que servirá para configurar algunas inyecciones. Crearemos una clase en un paquete `config` llamada `ApplicationConfiguration`.

    Debemos agregarle también las anotaciones de `@Configuration` y `@RequiredArgsConstructor`:

    ```java
    @Configuration
    @RequiredArgsConstructor
    public class ApplicationConfig {

    }
    ```

38. Ahora debemos crear un `@Bean` para el servicio que se necesita de `UserDetailsService`

    ```java
    private final IUserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
    return username -> userRepository.findByEmail(username)
               .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    ```

39. Con el `@Bean` creado, tenemos el requisito que dejamos pendiente en `JwtAuthenticationFilter`. Ahora, podemos terminar de valider el usuario, como sigue:

    ```java
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
          if(jwtService.isTokenValid(jwt,userDetails)) {
              UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                      userDetails,
                     null,
                      userDetails.getAuthorities()
              );
              authToken.setDetails(
                     new WebAuthenticationDetailsSource().buildDetails(request)
              );
              SecurityContextHolder.getContext().setAuthentication(authToken);
          }
    }
    ```

Con esto ya está el filtro configurado, y ya solo queda pasarle la batuta al siguiente filtro.
***
_La clase debería verse algo como lo que sigue:_

```java
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwt,userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
```

***

#### SecurityConfig

40. Aún con todo el trabajo hecho, falta una configuración que permita disparar todas las operaciones en el momento adecuado.
Para esto, haremos una nueva clase llamada `SecurityConfiguration`

    ```java
    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
        public class SecurityConfiguration {
    }
    ```

   **Las anotaciones `@Configuration` y `@EnableWebSecurity` deben siempre estar juntas.**

41. Antes de continuar, debemos definir si usaremos una lista de endpoints que no necesitan autenticación (como por
ejemplo, si se está registrando un usuario). Esto se puede hacer, primero definiendo el listado de los endpoints como
sigue con lo siguiente:

    ```java
    private static final String[] WHITE_LIST_URL = {
            "/api/v1/health", 
            "/api/v1/auth/**"
    };
    ```

42. También debemos crear la asociación para el uso del filtro `JwtAuthenticationFilter`

    ```java
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    ```

43. Por último, debemos agregar un proveedor de autenticación. Este tendrá que ser de tipo `AuthenticationProvider`, el
cual será un Bean en `ApplicationConfig`

    ```java
    private final AuthenticationProvider authenticationProvider;
    ```

#### De regreso a `ApplicationConfig`

44. En `ApplicationConfig` tendremos que hacer un Bean que se encargue de proveer la autenticación. Usaremos el tipo DAO.

    ```java
    @Bean
    public AuthenticationProvider authenticationProvider() {
       DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
       return authProvider;
    }   
    ```

   Hay que recordar que el `UserDetailsService` que se está usando es el que se definió en el paso 38.

45. También hay que agregar un encodificador para las contraseñas. Esto se hace con el Bean `passwordEncoder()`, que
puede ser como sigue:

    ```java
    @Bean
    private PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
    }
    ```

46. Por último, hay que crear un Bean que será de tipo `AuthenticationManager`, como sigue:

    ```java
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();        
    }
    ```

#### Controlador de autenticación

47. Ahora debemos configurar 2 endpoints que nos servirán para las tareas de autenticación (registrar y acceder). Para
esto, vamos a usar un controlador llamado `AuthenticationController`

    ```java
    @RestController
    @RequestMapping("/api/v1/auth")
    @RequiredArgsConstructor
    public class AuthenticationController {
    }   
    ```

48. Creamos 2 endpoints, uno para registro y el otro para autenticar:

    ```java
    @PostMapping("/register")
    public ResponseEntity<AutheticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        //
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AutenticationResponse> register(
           @RequestBody AuthenticationRequest request
    ) {
        //
    }
    ```

49. Tendremos que implementar `AuthenticationResponse`, `RegisterRequest`, y `AuthenticationRequest`
    1. Se debe crear la clase `AuthenticationResponse` que puede ser como sigue:

    ```java
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class AutheticationResponse {
        private String token;
    }
    ```

    2. La clase `RegisterRequest`, que puede entenderse como un DTO, se debe crear y podría ser como sigue _Este
    elemento puede ser un **record**_:

    ```java
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class RegisterRequest {
        private String firstName;
        private String lastName;
        private String email;
        private String password;
    }
    ```

    3. La clase `AuthenticationRequest` también puede ser un DTO, y podría implementarse como sigue:

    ```java
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class AuthenticationRequest {
        private String email;
        private String password;
    }
    ```

***
_El controlador `AuthenticationController` debería verse como sigue:_

```java
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
```

***

#### Servicio de Autenticación

50. Se debe crear ahora un servicio que manejará las operaciones de Autenticación. Este servicio puede estar en la clase
`AuthenticationService`. En la clase, hay que agregar las anotaciones de `@Service`y `@RequiredArgsConstructor`.

    ```java
    @Service
    @RequiredArgsConstructor
    public class AuthenticationService {
    }
    ```

51. Con este último servicio, se puede proceder a inyectar en `AuthenticationController` y usarlo para retornar los
objetos necesarios:

    ```java
    private final AuthenticationService service;
    
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    ```

    Notese que hay que implementar los métodos `register()` y `authenticate()` en el servicio.

52. Para el método `register()` hay que inyectar el repositorio (paso 15), el codificador de contraseña (paso 45) y el
servicio JWT (pasos 29-35) y se puede implementar como sigue:

    ```java
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ERole.STUDENT)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    ```

53. Para `authenticate()` se puede usar el Bean de `AuthenticationManager`(paso 46), que deberá ser inyectado y puede
implementarse como sigue:

    ```java
    private final AuthenticationManager authenticationManager;
    // ...
    
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    ```

***
_La clase `AuthenticationService` deberia estar parecido a lo siguiente:_

```java
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ERole.STUDENT)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
```

***

### Uso

Con toda la configuración cargada, se puede usar un cliente como Postman para realizar las operaciones HTTP. Hay que
tomar en consideración que el header de los requests que **no son registro de usuario nuevo** deben ir autenticados en
el header con el valor recibido después de acceder. El tipo de token que se debe usar debe ser _Bearer_.

### Otros recursos

* <www.youtube.com/watch?v=jQrExUrNbQE> - Autenticación basado en Roles
* <www.youtube.com/watch?v=RnZmeczS_DI> - Uso de las nuevas versiones de JWT
