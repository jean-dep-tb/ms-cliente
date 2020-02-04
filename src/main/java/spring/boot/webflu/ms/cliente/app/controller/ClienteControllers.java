package spring.boot.webflu.ms.cliente.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.Client;
import spring.boot.webflu.ms.cliente.app.documents.TipoCuentaClient;
import spring.boot.webflu.ms.cliente.app.service.ClienteService;
import spring.boot.webflu.ms.cliente.app.service.TipoCuentaClienteService;



@RequestMapping("/api/Clientes")
@RestController
public class ClienteControllers {

	@Autowired
	private ClienteService clientService;

	@Autowired
	private TipoCuentaClienteService tipoClienteService;

	//LISTA TODOS LOS CLIENTES
	@GetMapping
	public Mono<ResponseEntity<Flux<Client>>> findAll() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(clientService.findAllCliente())

		);
	}

	//LISTA CLIENTE POR ID
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Client>> viewId(@PathVariable String id) {
		return clientService.findByIdCliente(id)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	//LISTA CLIENTE POR DOCUMENTO
	@GetMapping("/dni/{dni}")
	public Mono<ResponseEntity<Client>> listarCLienteDni(@PathVariable String dni) {
		
		return clientService.viewDniCliente(dni)
				.map(p -> ResponseEntity
						.ok()
						.contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());		
	}

	//ACTUALIZA CLIENTE POR ID 
	@PutMapping
	public Mono<Client> updateCliente(@RequestBody Client cliente) {
		System.out.println(cliente.toString());
		return clientService.saveCliente(cliente);
	}
	
	//GUARDA CLIENTE VALIDANDO SI EL [TIPO CLIENTE] EXISTE
	@PostMapping
	public Mono<Client> guardarCliente(@RequestBody Client cli) {
		
		System.out.println(cli.getTipoCliente().getIdTipo());
		
		Mono<TipoCuentaClient> tipo = tipoClienteService.findByIdTipoCliente(cli.getTipoCliente().getIdTipo());
		
		System.out.println("Tipo de cliente ---> " + tipo.toString());
		
		return tipo.defaultIfEmpty(new TipoCuentaClient()).flatMap(c -> {
			if (c.getIdTipo() == null) {
				return Mono.error(new InterruptedException("NO EXISTE EL TIPO DE CUENTA"));
			}
			return Mono.just(c);
		}).flatMap(t -> {
			cli.setTipoCliente(t);
			return clientService.saveCliente(cli);
		});
	}

	//ELIMINA CLIENTE POR ID
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
		return clientService.findByIdCliente(id)
				.flatMap(s -> {
			return clientService.deleteCliente(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
	}

}



