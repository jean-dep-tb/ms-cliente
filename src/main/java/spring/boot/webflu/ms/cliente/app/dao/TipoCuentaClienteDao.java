package spring.boot.webflu.ms.cliente.app.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.TipoCuentaClient;

public interface TipoCuentaClienteDao extends ReactiveMongoRepository<TipoCuentaClient, String> {
	
	@Query("{'idTipo' : ?0}")
	public Mono<TipoCuentaClient> findByIdTipoCliente(String id);
	
}
