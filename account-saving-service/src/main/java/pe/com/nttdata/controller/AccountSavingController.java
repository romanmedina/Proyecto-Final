package pe.com.nttdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import pe.com.nttdata.model.AccountSaving;
import pe.com.nttdata.service.IAccountSavingService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/accounts/account_saving")
@Slf4j
public class AccountSavingController {

	@Autowired
	private IAccountSavingService accountSavingService;

	@GetMapping
	public ResponseEntity<Flux<AccountSaving>> findAll() {
		Flux<AccountSaving> a = accountSavingService.findAll();
		return new ResponseEntity<Flux<AccountSaving>>(a, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Flux<AccountSaving>> findById(@PathVariable Integer id) {
		Flux<AccountSaving> a = accountSavingService.findById(id);
		return new ResponseEntity<Flux<AccountSaving>>(a, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Mono<AccountSaving>> update(@RequestBody AccountSaving account_saving){
		Mono<AccountSaving> a = accountSavingService.updateAccSaving(account_saving);
		return new ResponseEntity<Mono<AccountSaving>>(a, HttpStatus.CREATED);
	}

	@PostMapping("/savingpersonal")
	public Mono<AccountSaving> saveAccSavingByClient(@RequestBody AccountSaving accountSaving) {
		log.info("*****Inicio: saveAccSavingByClient*****");
		Flux<AccountSaving> accountSav = accountSavingService.findByIdClient(accountSaving.getIdClient());
		Long cantidad = accountSav.count().block().longValue();
		return accountSavingService.saveAccSavingByClient(accountSaving, cantidad);

	}

	@PostMapping("/savingpersonalvip")
	public Mono<AccountSaving> saveAccSavingByClientVIP(@RequestBody AccountSaving accountSaving) {
		log.info("*****Inicio: saveAccSavingByClientVIP*****");
		Flux<AccountSaving> accountSav = accountSavingService.findByIdClient(accountSaving.getIdClient());
		Long cantidad = accountSav.count().block().longValue();
		return accountSavingService.saveAccSavingClientVIP(accountSaving, cantidad);

	}

	@PostMapping("/empresarialpyme")
	public Mono<AccountSaving> saveAccCurrentBussinessPyme(@RequestBody AccountSaving accountSaving) {
		log.info("*****Inicio: saveAccCurrentBussinessPyme*****");
		Flux<AccountSaving> accountSav = accountSavingService.findByIdClient(accountSaving.getIdClient());
		Long cantidad = accountSav.count().block().longValue();
		return accountSavingService.saveAccCurrentBussinessPyme(accountSaving, cantidad);

	}

	@DeleteMapping("/delete")
	public ResponseEntity<Mono<Void>> delete(@RequestBody AccountSaving account_saving) {
		Mono<Void> p = accountSavingService.delete(account_saving);
		return new ResponseEntity<Mono<Void>>(p, HttpStatus.NO_CONTENT);
	}

}
