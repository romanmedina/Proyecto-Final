package pe.com.nttdata.service;

import pe.com.nttdata.entity.Client;
import pe.com.nttdata.entity.ClientResilence;
import pe.com.nttdata.entity.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
	
	Flux<Client> findAll();
	Mono<Client> findById(String id);
	Mono<Client> create(Client client);
//	Mono<Client> update(Client client);
//	Mono<Client> save(Client client);
	//Resilence4j
	Mono<ClientResilence> getClientById(Integer id);
	Mono<Person> getPersonById(Integer id);
}
