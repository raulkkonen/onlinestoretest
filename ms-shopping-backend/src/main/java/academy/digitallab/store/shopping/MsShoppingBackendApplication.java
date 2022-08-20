package academy.digitallab.store.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients

@SpringBootApplication
public class MsShoppingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsShoppingBackendApplication.class, args);
	}

}
