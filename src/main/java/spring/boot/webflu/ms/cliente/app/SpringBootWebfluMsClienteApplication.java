package spring.boot.webflu.ms.cliente.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import reactor.core.publisher.Flux;
import spring.boot.webflu.ms.cliente.app.documents.Client;
import spring.boot.webflu.ms.cliente.app.documents.TipoCuenta;
import spring.boot.webflu.ms.cliente.app.documents.TypeClient;
import spring.boot.webflu.ms.cliente.app.service.ClienteService;
import spring.boot.webflu.ms.cliente.app.service.TipoClienteService;

@EnableEurekaClient
@SpringBootApplication
public class SpringBootWebfluMsClienteApplication implements CommandLineRunner{
	
	@Autowired
	private ClienteService serviceCliente;
	
	@Autowired
	private TipoClienteService serviceTipoCliente;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluMsClienteApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluMsClienteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		mongoTemplate.dropCollection("Clientes").subscribe();
		mongoTemplate.dropCollection("TipoCliente").subscribe();
		
		TypeClient personal = new TypeClient("1","personal");
		TypeClient empresa = new TypeClient("2","empresa");
		TypeClient vip = new TypeClient("3","vip");
		TypeClient pyme = new TypeClient("4","pyme");
		TypeClient corporativo = new TypeClient("5","corporativo");
		//
		Flux.just(personal,empresa,vip,pyme,corporativo)
		.flatMap(serviceTipoCliente::saveTipoCliente)
		.doOnNext(c -> {
			log.info("Tipo cliente creado: " +  c.getDescripcion() + ", Id: " + c.getIdTipo());
		}).thenMany(					
				Flux.just(
						new Client("07091424","JUAN CARLOS",personal),
						new Client("47305710","ESMERALDA CORP",empresa),
						new Client("12345678","EVERIS SAC",vip),
						new Client("87654321","LUIS MIGUEL",pyme),
						new Client("23456789","CIVA",corporativo)

						)					
					.flatMap(client -> {
						return serviceCliente.saveClientePersonal(client);
					})					
				).subscribe(client -> log.info("Insert: " + client.getId() + " " + client.getNombres()));
		
	}

}
