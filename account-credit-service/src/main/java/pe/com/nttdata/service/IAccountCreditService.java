package pe.com.nttdata.service;

import pe.com.nttdata.model.AccountCredit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountCreditService {
	
	Flux<AccountCredit> findAll();
	Flux<AccountCredit> findById(Integer id);
	Flux<AccountCredit> findByIdClient(String idClient);
	Mono<AccountCredit> create(AccountCredit fixed);
	Mono<AccountCredit> depositCredit(AccountCredit fixed);
	Mono<AccountCredit> retreatCredit(AccountCredit fixed);
	Mono<Void> delete(AccountCredit fixed);
	//
	Mono<AccountCredit> saveAccountCreditByClient(AccountCredit accountCredit, Long cantidad);
}
