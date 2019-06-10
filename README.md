# How to test the layers of a Spring Boot Application

Simple Spring-Boot Testing showcase. 


## Run the application

### Maven

#### System Maven
```
$ mvn spring-boot:run
```
#### Maven Wrapper
```
$ ./mvnw spring-boot:run
```
#### Docker

Check the Dockerfile how to use a multistage build to containerize Maven Projects (environment agnostic) 

```
$ docker build -t javamark/my-app .
$ docker run -p 8080:8080 javamark/my-app
```

### interact with api

```
# add a book
curl -X POST http://localhost:8080/api/books -H 'Content-Type: application/json' -d '{"name": "Coraline", "author": "Neil Gaiman"}'

# get all books
curl -X GET http://localhost:8080/api/books

# get a single book by id
curl -X GET http://localhost:8080/api/books/1
```

## Run tests

System maven:

```
$ mvn clean verify
```

Maven Wrapper:

```
$ ./mvnw clean verify
```


## Persistence Layer - BookRepository

[BookRepositoryIntegrationTest.java](src/test/java/de/javamark/springboot/examples/repositories/BookRepositoryIntegrationTest.java)

```
@RunWith(SpringRunner.class)
```
* Required for Spring Boot Testing feature 

```
@DataJpaTest
```
* configures an in-memory database, so we don't have to mock database access
* setup Spring Data (Hibernate as well as a DataSource)


## Service Layer - BookService

- is dependent on persistence layer

[BookServiceImplTest.java](src/test/java/de/javamark/springboot/examples/services/BookServiceImplTest.java)

```
@RunWith(SpringRunner.class)
```
* Required for Spring Boot Testing feature 

```
@MockBean
private BookRepository bookRepository;
```
* Mocks dependencies to persistence layer using Spring Boot's mocking support 

## BookController

- is dependent on service layer

[BookRestControllerIntegrationTest.java](src/test/java/de/javamark/springboot/examples/controller/BookRestControllerIntegrationTest.java)

```
@RunWith(SpringRunner.class)
```
* Required for Spring Boot Testing feature 

```
@WebMvcTest(BookController.class)
```
* auto-configures Spring MVC for the BookController
* covers integration with Service Layer


## Integration Testing without mocking!

@Note: integration tests should be placed in a separate directory (e.g.: itest) and configured using maven plugins

- to test the whole application

[BookRestControllerIntegrationTest.java](src/test/java/de/javamark/springboot/examples/controller/BookRestControllerIntegrationTest.java)

```
@SpringBootTest
```
* bootstraps the entire application
* webEnvironment: configures the runtime environment


```
@TestPropertySource
```
* provides a test configuration
* to override existing properties



