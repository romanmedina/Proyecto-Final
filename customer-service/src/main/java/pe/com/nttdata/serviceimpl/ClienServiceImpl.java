package pe.com.nttdata.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import pe.com.nttdata.entity.Client;
import pe.com.nttdata.entity.ClientResilence;
import pe.com.nttdata.entity.Person;
import pe.com.nttdata.repository.IClientRepository;
import pe.com.nttdata.service.IClientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienServiceImpl implements IClientService{

	private final WebClient webClient;
	
	public ClienServiceImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://localhost:8087").build();
	}

	@Autowired
	IClientRepository clientRepo;
	
	@Override
	public Flux<Client> findAll() {
		return clientRepo.findAll();
	}

	@Override
	public Mono<Client> create(Client client) {
		return clientRepo.save(client);
	}

	@Override
	public Mono<Client> findById(String id) {
		return clientRepo.findById(id);
	}

//	@Override
//	public Mono<Client> update(Client client) {
//		return clientRepo.save(client);
//	}

//	@Override
//	public Mono<Client> save(Client client) {
//		
//		if (!(ClientType.PERSONAL.equals(client.getType()) || ClientType.BUSINESS.equals(client.getType()))) {
//			log.info("El campo idTypeClient debe ser de tipo: 0 Personal, 1 Empresarial");
//			return null;
//		}else {		
//			return clientRepo.save(client);
//		}
//		
//	}

	@Override
	public Mono<ClientResilence> getClientById(Integer id) {
		Person person = new Person();
		Mono<Person> personByClient = this.webClient.get().uri("/api/v1/person/{id}", id).retrieve().bodyToMono(Person.class);
		return personByClient.flatMap(x ->{
			person.setIdPerson(x.getIdPerson());
			person.setFullName(x.getFullName());
			person.setTypeDoc(x.getTypeDoc());
			person.setNumberDoc(x.getNumberDoc());
			person.setDateBirth(x.getDateBirth());
			person.setEmail(x.getEmail());
			person.setPhone(x.getPhone());
			person.setActive(x.getActive());
			
			Mono<ClientResilence> clientResilence = Mono.just(new ClientResilence(10,"PERSONAL","2022-08-30T12:26:30.107","ROM01","10.21.12.122",person));
			return clientResilence;
		});
	}	
	
	@Override
	public Mono<Person> getPersonById(Integer id) {
		return this.webClient.get().uri("/api/v1/person/{id}", id).retrieve().bodyToMono(Person.class);
	}

	
}
