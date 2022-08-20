package academy.digitallab.store.security.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisCFG {
    private String serverHost;
    private Integer serverPort;
    private Integer sessionSecondsLive;
}