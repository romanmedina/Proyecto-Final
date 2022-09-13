package pe.com.nttdata.service;

import pe.com.nttdata.model.AccountSaving;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountSavingService {
	
	Flux<AccountSaving> findAll();
	Mono<AccountSaving> getById(Integer id);
	Flux<AccountSaving> findById(Integer id);
	Flux<AccountSaving> findByIdClient(String idClient);
	Mono<AccountSaving> saveAccSavingByClient(AccountSaving accountSaving, Long cantidad);	
	Mono<AccountSaving> saveAccSavingClientVIP(AccountSaving accountSaving, Long cantidad);
	Mono<AccountSaving> saveAccCurrentBussinessPyme(AccountSaving accountSaving, Long cantidad);
//	Mono<AccountSaving> depositAccSaving(AccountSaving accountSaving);
//	Mono<AccountSaving> retreatAccSaving(AccountSaving accountSaving);
	Mono<AccountSaving> updateAccSaving(AccountSaving accountSaving);
	Mono<Void> delete(AccountSaving account_saving);
}
