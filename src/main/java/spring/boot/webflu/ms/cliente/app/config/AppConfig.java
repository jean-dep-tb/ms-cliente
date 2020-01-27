package spring.boot.webflu.ms.cliente.app.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	
	@Bean
	@Qualifier("person")
	public WebClient registrarWebClientPerson() {
		return WebClient.create("http://localhost:8021/api/person");
	}
	
	@Bean
	@Qualifier("operations")
	public WebClient registrarWebClientAdmin() {
		return WebClient.create("http://localhost:8029/api/accountOperations");
	}

}
