package spring.boot.webflu.ms.cliente.app.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.Client;

public interface ClienteService {

	Flux<Client> findAllClientePersonal();
	Mono<Client> findByIdClientePersonal(String id);
	Mono<Client> saveClientePersonal(Client clientePersonal);
	Mono<Void> deleteCliente(Client cliente);
	
	Mono<Client> viewDniCliente(String dni);
	
}
