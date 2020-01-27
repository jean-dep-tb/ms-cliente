package spring.boot.webflu.ms.cliente.app.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.TypeClient;

public interface TipoClienteDao extends ReactiveMongoRepository<TypeClient, String> {
	
	@Query("{'idTipo' : ?0}")
	public Mono<TypeClient> findByIdTipoCliente(String id);
	
}
