package spring.boot.webflu.ms.cliente.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.dao.ClienteDao;
import spring.boot.webflu.ms.cliente.app.documents.Client;
import spring.boot.webflu.ms.cliente.app.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	public ClienteDao clienteDao;
	
	@Override
	public Flux<Client> findAllClientePersonal()
	{
	return clienteDao.findAll();
	
	}
	@Override
	public Mono<Client> findByIdClientePersonal(String id)
	{
	return clienteDao.findById(id);
	
	}
	
	@Override
	public Mono<Client> viewDniCliente(String dni)
	{
	return clienteDao.viewDniCliente(dni);
	
	}
	
	@Override
	public Mono<Client> saveClientePersonal(Client clientePersonal)
	{
		return clienteDao.save(clientePersonal);
	}
	
	@Override
	public Mono<Void> deleteCliente(Client cliente) {
		return clienteDao.delete(cliente);
	}
	
}
