package pe.com.nttdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j2;
import pe.com.nttdata.entity.Client;
import pe.com.nttdata.entity.ClientResilence;
import pe.com.nttdata.entity.Person;
import pe.com.nttdata.service.IClientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/clients")
@Log4j2
public class ClientController {

	@Autowired
	private IClientService clientService;
	
	@GetMapping
	public ResponseEntity<Flux<Client>> getAll(){
		Flux<Client> getAll = clientService.findAll();
		log.info("*************************************************************");
		log.info("*****Inicio: Listar Cliente*****");
		log.info("***** "+ getAll +" *****");
		log.info("*************************************************************");
		return new ResponseEntity<Flux<Client>>(getAll,HttpStatus.OK);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Mono<Client>> findById(@PathVariable String id){
    	Mono<Client> c = clientService.findById(id);
        return new ResponseEntity<Mono<Client>>(c, HttpStatus.OK);
    }
    
	@PostMapping
	public ResponseEntity<Mono<Client>> create (@RequestBody Client client){
		log.info("*****Inicio: Crear Cliente*****");
		log.info("*************************************************************");
		Mono<Client> c = clientService.create(client);
		return new ResponseEntity<Mono<Client>>(c, HttpStatus.CREATED);
	}
	
//	@PutMapping
//	public ResponseEntity<Mono<Client>> update (@RequestBody Client client){
//		Mono<Client> c = clientService.update(client);
//		return new ResponseEntity<Mono<Client>>(c, HttpStatus.CREATED);
//	}
	
	@GetMapping("/clientperson/{id}")
	@CircuitBreaker(name = "person", fallbackMethod = "fallBackGetClientByid")
	public Mono<ClientResilence> getClientById(@PathVariable Integer id){
		return clientService.getClientById(id);
	}
	
	@GetMapping("/getperson/{id}")
	public Mono<Person> getPersonByIdClient(@PathVariable Integer id){
		return clientService.getPersonById(id);
	}
	
	public Mono<String> fallBackGetClientByid(Integer id, RuntimeException runtimeException){
		return Mono.just("Microservicio <Person> no está respondiendo");
	}
	
}
