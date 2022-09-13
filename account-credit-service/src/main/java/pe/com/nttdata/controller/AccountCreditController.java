package pe.com.nttdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.log4j.Log4j2;
import pe.com.nttdata.model.AccountCredit;
import pe.com.nttdata.service.IAccountCreditService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/accounts/account_credit")
@Log4j2
public class AccountCreditController {

	@Autowired
	private IAccountCreditService accountCreditService;

	@GetMapping
	public ResponseEntity<Flux<AccountCredit>> getAll() {
		log.info("*************************************************************");
		log.info("*****Inicio: Listar Cuenta de crédito*****");
		log.info("*************************************************************");
		Flux<AccountCredit> getAll = accountCreditService.findAll();
		return new ResponseEntity<Flux<AccountCredit>>(getAll, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Flux<AccountCredit>> findById(@PathVariable Integer id) {
		log.info("*************************************************************");
		log.info("*****Inicio: Listar Cuenta de crédito por id*****");
		log.info("*************************************************************");
		Flux<AccountCredit> p = accountCreditService.findById(id);
		return new ResponseEntity<Flux<AccountCredit>>(p, HttpStatus.OK);
	}

	@GetMapping("/client/{id}")
	public ResponseEntity<Flux<AccountCredit>> findByIdClient(@PathVariable String id) {
		log.info("*************************************************************");
		log.info("*****Inicio: Listar Cuenta de crédito por id*****");
		log.info("*************************************************************");
		Flux<AccountCredit> p = accountCreditService.findByIdClient(id);
		return new ResponseEntity<Flux<AccountCredit>>(p, HttpStatus.OK);
	}

	@PostMapping
	public Mono<AccountCredit> create(@RequestBody AccountCredit account_saving) {
		return accountCreditService.create(account_saving);
	}

	@PostMapping("/creditpersonal")
	public Mono<AccountCredit> saveAccCreditByClient(@RequestBody AccountCredit accountCredit) {
		log.info("*****Inicio: saveAccCreditByClient*****");
		Flux<AccountCredit> accountCred = accountCreditService.findByIdClient(accountCredit.getIdClient());
		Long cantidad = accountCred.count().block().longValue();
		return accountCreditService.saveAccountCreditByClient(accountCredit, cantidad);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Mono<Void>> delete(@RequestBody AccountCredit account_saving) {
		Mono<Void> p = accountCreditService.delete(account_saving);
		return new ResponseEntity<Mono<Void>>(p, HttpStatus.NO_CONTENT);
	}

}
