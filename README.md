# Spring Boot - Microservice
This was initialy made by following this great tutorial series:


### Note about the tutorial
- To make it work with Eureka, in the Clients, the property where eureka server url is configure must be **defaultZone** instead of *default-zone* as it is in the Tutorial



### Securing Eureka Dashboard
A simple way to secure it is using *Basic Authentication* in order to do so:
- Add spring security to Gradle

```
compile('org.springframework.boot:spring-boot-starter-security')
```
