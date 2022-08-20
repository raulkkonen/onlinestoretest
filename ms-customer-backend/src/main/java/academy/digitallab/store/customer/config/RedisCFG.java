package academy.digitallab.store.customer.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisCFG {
    private String serverHost;
    private Integer serverPort;
    private Integer sessionSecondsLive;
}