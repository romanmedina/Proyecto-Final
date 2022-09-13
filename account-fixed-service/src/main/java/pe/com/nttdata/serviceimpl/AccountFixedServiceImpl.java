package pe.com.nttdata.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import pe.com.nttdata.client.CustomerClient;
import pe.com.nttdata.model.AccountFixed;
import pe.com.nttdata.model.Client;
import pe.com.nttdata.model.ClientType;
import pe.com.nttdata.repository.IAccountFixedRepository;
import pe.com.nttdata.service.IAccountFixedService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class AccountFixedServiceImpl implements IAccountFixedService{

	@Autowired
	IAccountFixedRepository fixedRepo;
	
	@Autowired
	private CustomerClient customerClient;
	
	@Override
	public Flux<AccountFixed> findAll() {
		return fixedRepo.findAll();
	}

	@Override
	public Flux<AccountFixed> findById(Integer id) {
		return fixedRepo.findAll().filter(x -> x.getIdFixed().equals(id));
	}
	
	@Override
	public Flux<AccountFixed> findByIdClient(Integer idClient) {
		return fixedRepo.findAll().filter(x -> x.getIdClient().equals(idClient));
	}

	@Override
	public Mono<AccountFixed> create(AccountFixed fixed) {
		return fixedRepo.save(fixed);
	}

	@Override
	public Mono<AccountFixed> depositFixed(AccountFixed fixed) {
		return fixedRepo.save(fixed);
	}

	@Override
	public Mono<AccountFixed> retreatFixed(AccountFixed fixed) {
		return fixedRepo.save(fixed);
	}

	/*
	 * Un cliente personal puede tener cuentas a plazo fijo.
	 */
	@Override
	public Mono<AccountFixed> saveAccountFixedByClient(AccountFixed accountFixed) {
		log.info("*****INICIO: Crear cuenta a plazo fijo por persona*****");
		Client customer = customerClient.get(accountFixed.getIdClient()).getBody();
			if(customer.getType().equals(ClientType.PERSONAL)) {		
				log.info("**EXITO: Cuenta de plazo fijo creada para el cliente PERSONAL**");
				return fixedRepo.save(accountFixed);
			}else {
				log.info("**ERROR: El cliente no es de tipo PERSONAL**");
				return null;
			}
	}
}
