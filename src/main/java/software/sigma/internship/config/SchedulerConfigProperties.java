package software.sigma.internship.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties("scheduler")
@Component("schedulerProperties")
@Getter
@Setter
public class SchedulerConfigProperties {

    private String cronDaily;
}
