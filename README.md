# Spring Boot - Microservice
This was initialy made by following this great tutorial series: [Microservices with Spring Boot](http://www.springboottutorial.com/creating-microservices-with-spring-boot-part-1-getting-started)

## See it in action
Go to http://localhost:8100/currency-converter/from/EUR/to/ARG/quantity/10000 (previously load some currency conversion in the database). This will act on the *CurrencyConverterService* wich will requiere *ForexService* to get the conversion rate.

And to see the Eureka Dashboard just go to http://localhost:8765 (notice I've change the deafult port wich is 8761). There are other URLs but I didn't dig on them */info* and */health*. Also notice that credentials to access the dashboard are newuser and newpassword according to the security configuration below

### Note about the tutorial
- To make it work with Eureka, in the Clients, the property where eureka server url is configure must be **defaultZone** instead of *default-zone* as it is in the Tutorial



### Securing Eureka Dashboard
- *[Using Netflix Eureka with Spring Cloud](http://www.thomas-letsch.de/2015/using-netflix-eureka-with-spring-cloud/)*
- *[Eureka Security Examples](https://github.com/steve-oakey/eureka-security-example)*
- *[Spring Cloud Securing Services](http://www.baeldung.com/spring-cloud-securing-services)*

A simple way to secure it is using *Basic Authentication* in order to do so:
- Add spring security to Gradle

```
compile('org.springframework.boot:spring-boot-starter-security')
```

- Set Basic Authentication (this can be done by two ways)
  1. Method 1: In *application.properties*
```  
security.basic.enabled= true
security.user.name= myuser
security.user.password= mypassword
```
  2. Method 2: Internal Configuration. Create a *SecurityConfig* class
```  
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    public static class WebSecurty extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("myuser").password("mypassword").roles("USER");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
        }
    }
}
```

- Finally, and I guess it's related to both above methods (but maybe it's not necessary with method 1). In the Configuration of the HTTP Security we need to add **.and().csrf().disable()** according to [this thread](https://github.com/spring-cloud/spring-cloud-netflix/issues/2754)


### Advance Security Configurations
- Continuing reading on *[Spring Cloud Securing Services](http://www.baeldung.com/spring-cloud-securing-services)* could bring more security to the system
- And also [Using Netflix Eureka with Spring Cloud](http://www.thomas-letsch.de/2015/using-netflix-eureka-with-spring-cloud/) talks about SSL implementation
- I think [Secure Discovery with Spring Cloud Netflix Eureka](https://piotrminkowski.wordpress.com/2018/05/21/secure-discovery-with-spring-cloud-netflix-eureka/) is deeper with SSL
