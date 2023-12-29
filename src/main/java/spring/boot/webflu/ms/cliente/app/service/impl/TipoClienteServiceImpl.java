package spring.boot.webflu.ms.cliente.app.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.dao.TipoClienteDao;
import spring.boot.webflu.ms.cliente.app.documents.TypeClient;
import spring.boot.webflu.ms.cliente.app.service.TipoClienteService;

@Service
public class TipoClienteServiceImpl implements TipoClienteService{

	
	@Autowired
	public TipoClienteDao tipoClienteDao;
	
	@Override
	public Flux<TypeClient> findAllTipoCliente()
	{
	return tipoClienteDao.findAll();
	
	}
	@Override
	public Mono<TypeClient> findByIdTipoCliente(String id)
	{
	return tipoClienteDao.findByIdTipoCliente(id);
	
	}
	
	@Override
	public Mono<TypeClient> saveTipoCliente(TypeClient tipoCliente)
	{
	return tipoClienteDao.save(tipoCliente);
	}
}
