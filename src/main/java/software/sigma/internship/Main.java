package software.sigma.internship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import software.sigma.internship.config.SchedulerConfigProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(SchedulerConfigProperties.class)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
