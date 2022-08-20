package academy.digitallab.store.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@RefreshScope

@SpringBootApplication
public class MsSecurityBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSecurityBackendApplication.class, args);
	}

}
