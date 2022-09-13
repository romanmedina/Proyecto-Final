package pe.com.nttdata.controller;

import java.util.Iterator;
import java.util.List;
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
import pe.com.nttdata.model.Movement;
import pe.com.nttdata.service.IMovementService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/accounts/movement")
@Slf4j
public class GenerateMovementController {

	@Autowired
	private IMovementService movementService;

	@GetMapping
	public ResponseEntity<Flux<Movement>> getAll() {
		Flux<Movement> getAll = movementService.getAll();
		log.info("*************************************************************");
		log.info("*****Inicio: Listar Movimientos*****");
		log.info("*************************************************************");
		return new ResponseEntity<Flux<Movement>>(getAll, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Flux<Movement>> findById(@PathVariable Integer id) {
		Flux<Movement> p = movementService.findById(id);
		return new ResponseEntity<Flux<Movement>>(p, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Mono<Movement>> update(@RequestBody Movement Movement) {
		Mono<Movement> p = movementService.update(Movement);
		return new ResponseEntity<Mono<Movement>>(p, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Mono<Void>> delete(@RequestBody Movement Movement) {
		Mono<Void> p = movementService.delete(Movement);
		return new ResponseEntity<Mono<Void>>(p, HttpStatus.NO_CONTENT);
	}

	@SuppressWarnings("null")
	@GetMapping("movementclient/{idClient}")
	public ResponseEntity<Flux<Movement>> findByIdProductBankClient(@PathVariable String idClient) {
		Flux<AccountSaving> objFluxaccountSavingClient = movementService.findByIdClient(idClient);
		List<AccountSaving> listAccountSavingClient = objFluxaccountSavingClient.collectList().block();
		List<Movement> listMovementClient = null;
		List<Movement> listMovementAll = null;
		for (Iterator<AccountSaving> iterator = listAccountSavingClient.iterator(); iterator.hasNext();) {
			AccountSaving accountSaving = (AccountSaving) iterator.next();
			Flux<Movement> objFluxAccountMovement = movementService.findByIdSaving(accountSaving.getIdSaving());
			listMovementClient = objFluxAccountMovement.collectList().block();
			listMovementAll.addAll(listMovementClient);
		}
		Flux<Movement> fluxAll = Flux.fromIterable(listMovementAll);
		return new ResponseEntity<Flux<Movement>>(fluxAll, HttpStatus.OK);
	}

	// Ahorro: libre de comisión por mantenimiento y con un límite máximo de
	// movimientos mensuales.
	@PostMapping
	public ResponseEntity<Mono<Movement>> create(@RequestBody Movement movement) {
		Mono<Movement> saving = movementService.create(movement);
		if (saving == null) {
			return new ResponseEntity<Mono<Movement>>(saving, HttpStatus.PRECONDITION_FAILED);
		} else {
			return new ResponseEntity<Mono<Movement>>(saving, HttpStatus.CREATED);
		}
	}
}
