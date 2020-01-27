package spring.boot.webflu.ms.cliente.app.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.TypeClient;

public interface TipoClienteService {
	
	Flux<TypeClient> findAllTipoCliente();
	Mono<TypeClient> findByIdTipoCliente(String id);
	Mono<TypeClient> saveTipoCliente(TypeClient tipoCliente);
	
}
