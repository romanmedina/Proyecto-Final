package pe.com.nttdata.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import pe.com.nttdata.client.CustomerClient;
import pe.com.nttdata.model.AccountCredit;
import pe.com.nttdata.model.Client;
import pe.com.nttdata.model.ClientType;
import pe.com.nttdata.repository.IAccountCreditRepository;
import pe.com.nttdata.service.IAccountCreditService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AccountCreditServiceImpl implements IAccountCreditService{

	@Autowired
	private IAccountCreditRepository creditRepo;
	
	@Autowired
	private CustomerClient customerClient;
	
	@Override
	public Flux<AccountCredit> findAll() {
		return creditRepo.findAll();
	}

	@Override
	public Flux<AccountCredit> findById(Integer id) {
		return creditRepo.findAll().filter(x -> x.getIdCredit().equals(id));
	}
	
	@Override
	public Flux<AccountCredit> findByIdClient(String idClient) {
		return creditRepo.findAll().filter(x -> x.getIdClient().equals(idClient));
	}
	
	@Override
	public Mono<AccountCredit> create(AccountCredit Credit) {
		return creditRepo.save(Credit);
	}

	@Override
	public Mono<AccountCredit> depositCredit(AccountCredit Credit) {
		return creditRepo.save(Credit);
	}
	
	@Override
	public Mono<AccountCredit> retreatCredit(AccountCredit Credit) {
		return creditRepo.save(Credit);
	}

	@Override
	public Mono<Void> delete(AccountCredit credit) {
		return creditRepo.delete(credit);
	}
/*
 * Personal: solo se permite un solo crédito por persona.
 */
	@Override
	public Mono<AccountCredit> saveAccountCreditByClient(AccountCredit accountCredit, Long cantidad) {
		log.info("*****INICIO: Crear cuenta de credito por persona*****");
		Client customer = customerClient.get(accountCredit.getIdClient()).getBody();
		if(customer.getType().equals(ClientType.PERSONAL)) {
			if(!(cantidad > 0)) {
				log.info("**EXITO: Se creó correctamente la cuenta de crédito PERSONAL**");
				return creditRepo.save(accountCredit);
			}else {
				log.info("**ERROR: Ya existe una cuenta de crédito para el cliente**");
				return null;
			}
		}else {
			log.info("**ERROR: El cliente no es de tipo PERSONAL**");
			return null;
		}
	}
}
