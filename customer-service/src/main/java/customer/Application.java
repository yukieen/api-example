package customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/***
 * customer-service
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
