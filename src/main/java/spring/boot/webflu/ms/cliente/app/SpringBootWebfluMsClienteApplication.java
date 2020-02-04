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
import spring.boot.webflu.ms.cliente.app.documents.TipoCuentaClient;
import spring.boot.webflu.ms.cliente.app.service.ClienteService;
import spring.boot.webflu.ms.cliente.app.service.TipoCuentaClienteService;

@EnableEurekaClient
@SpringBootApplication
public class SpringBootWebfluMsClienteApplication implements CommandLineRunner{
	
	@Autowired
	private ClienteService serviceCliente;
	
	@Autowired
	private TipoCuentaClienteService serviceTipoCliente;
	
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
		
		TipoCuentaClient personal = new TipoCuentaClient("1","personal");
		TipoCuentaClient empresa = new TipoCuentaClient("2","empresa");
		TipoCuentaClient vip = new TipoCuentaClient("3","vip");
		TipoCuentaClient pyme = new TipoCuentaClient("4","pyme");
		TipoCuentaClient corporativo = new TipoCuentaClient("5","corporativo");
		//
		Flux.just(personal,empresa,vip,pyme,corporativo)
		.flatMap(serviceTipoCliente::saveTipoCliente)
		.doOnNext(c -> {
			log.info("Tipo cliente creado: " +  c.getDescripcion() + ", Id: " + c.getIdTipo());
		}).thenMany(					
				Flux.just(
						new Client("47305711","JUAN CARLOS",personal,"BCP"),
						new Client("47305710","ESMERALDA CORP",empresa,"BBVA"),
						new Client("12345678","EVERIS SAC",vip,"BBVA"),
						new Client("87654321","LUIS MIGUEL",pyme,"BCP"),
						new Client("23456789","CIVA",corporativo,"XXX")

						)					
					.flatMap(client -> {
						return serviceCliente.saveCliente(client);
					})					
				).subscribe(client -> log.info("Insert: " + client.getId() + " " + client.getNombres()));
		
	}

}
