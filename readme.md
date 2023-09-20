### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### Your experience in Java

Please let us know more about your Java experience in a few sentences. For example:

- I have 3 years experience in Java and I started to use Spring Boot from last year
- I'm a beginner and just recently learned Spring Boot
- I know Spring Boot very well and have been using it for many years

#### My profile
- Tsuyoshi Kaburagi
- I am a completely beginner for Spring Boot and I have not any experience of coding using the technology before.
- (This is the first time for me to use spring boot.)
- However, I have experience to develop PHP based APIs for my ex-company.

#### What I have done
- Introduction of JWT to make the API's endpoints more secure.
- Due to the introduction of JWT, Swagger is not available at the moment.
- Could you please use the api client application like postman to call the APIs?
- The user authentication for JWT is hard coded and fixed.
- Bug fix and add some error handling (not complete one) 
- (I assumed that Local DB will not be down and the connection of DB is always fine.)
- Added some validation.
- Added Junit test cases.

#### Instruction to use JWT
- 1. Generate JWT by calling following Api.
- POST: http://localhost:8080/api/v1/authenticate
- {"username" : "axa", password: "axa"}
- Response
  {
	"jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJheGEiLCJleHAiOjE2OTYwNzg5NDgsImlhdCI6MTY5NTAzOTcxOX0.u7YdqaLaCP9Xthdpypiaei2Nr6eSOZZz0fH9Otz1hKw"
  }
  
- 2. Copy the token, then when calling the other API, could you please the jwt token into following place?

- Header:
- Authorization: Bearer jwttoken(generated in step 1)
  
  E.g.
- Header:
- Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJheGEiLCJleHAiOjE2OTYwNzg5NDgsImlhdCI6MTY5NTAzOTcxOX0.u7YdqaLaCP9Xthdpypiaei2Nr6eSOZZz0fH9Otz1hKw
  
  
#### What I have not done completely
- Error handing and Message handing.
- Adding logs
- Caching for data manipulation.
- Validation tests on JUnits
- End to End Rest api tests on Junits because of including Jwt mechanism. 

#### Issues that I am facing
- lombok does not work on my work environment. Therefore, it is necessary for me to create getter,setter and constructor as well.
