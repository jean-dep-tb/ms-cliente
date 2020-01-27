package spring.boot.webflu.ms.cliente.app.controller;

import javax.annotation.PostConstruct;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import spring.boot.webflu.ms.cliente.app.dto.CurrentAccount;

@RestController
@RequestMapping("/api/Clientes")
public class WebClientController {
	
	WebClient webClient;
	@PostConstruct
	 public void init() { 
			 webClient = WebClient 
			.builder()
			.baseUrl("http://localhost:8002/api/ProductoBancario")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
			.build(); 		 
	}

	//proyecto 3
   @GetMapping("/ProductoBancario/{dniCliente}")
   public Flux<CurrentAccount> getFamilyList(@PathVariable String dniCliente) 
   { 
		return webClient.get().uri("/dni/"+dniCliente).retrieve().bodyToFlux(CurrentAccount.class); 
   }
  

}
