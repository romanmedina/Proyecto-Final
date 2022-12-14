package pe.com.nttdata.serviceimpl;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import pe.com.nttdata.model.AccountSaving;
import pe.com.nttdata.model.Comission;
import pe.com.nttdata.model.Movement;
import pe.com.nttdata.repository.IAccountSavingRepository;
import pe.com.nttdata.repository.IMovementRepository;
import pe.com.nttdata.service.IAccountSavingService;
import pe.com.nttdata.service.IComissionService;
import pe.com.nttdata.service.IMovementService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MovementServiceImpl implements IMovementService{

	@Autowired 
	IMovementRepository movementRepo;
	@Autowired 
	IAccountSavingRepository accSavingRepo;
	
	@Autowired
	IAccountSavingService accountSavingService;

	@Autowired
	IComissionService comissionService;
	
	@Override
	public Flux<Movement> getAll() {
		return movementRepo.findAll();
	}

	@Override
	public Mono<Movement> create(Movement movement) {
		
		log.info("*****Inicio: Crear Movimiento Ahorros*****");
		log.info("*************************************************************");
		Mono<Movement> saving = null;
		// No busca en la tabla de comision "Libre de comission"
		int month = LocalDate.now().getMonthValue();
		Integer intNumberMovMax = 0;
		Double dblBalance = 0.00;
		Double dblNewBalance = 0.00;
		Flux<AccountSaving> objFluxAccountSaving = accountSavingService.findById(movement.getIdSaving());
		List<AccountSaving> listAccountSaving = objFluxAccountSaving.collectList().block();
		for (Iterator<AccountSaving> iterator = listAccountSaving.iterator(); iterator.hasNext();) {
			AccountSaving accountSaving = (AccountSaving) iterator.next();
			intNumberMovMax = accountSaving.getNumberMovMonth();
			dblBalance = accountSaving.getBalance();
		}
		// Validando si movement.numberMovMax() es cero o null entonces valida lo anterior 
		if (intNumberMovMax!=null && intNumberMovMax>0) {
			// Todas las cuentas bancarias tendr??n un n??mero m??ximo de transacciones (dep??sitos y retiros) 
			// que no cobrar?? comisi??n y superado ese n??mero se cobrar?? comisi??n por cada transacci??n realizada.
			Flux<Movement> objFluxMovement = this.findByIdSaving(movement.getIdSaving());
			if (objFluxMovement.count().block().longValue()+1>intNumberMovMax) {
				// Grabando el movimiento
				saving = movementRepo.save(movement);
				// Aplicando comision grabando movimiento
				Flux<Comission> objComission = comissionService.findByIdSaving(movement.getIdSaving());
				dblNewBalance = dblBalance*(objComission.blockFirst().getRate()/100);
				movement.setBalance(dblNewBalance);
				saving = movementRepo.save(movement);
			}
		} else { // Si movement.numberMovMax() es cero o null validando lo anterior 
			Flux<Movement> objFluxMovement = this.findByIdSavingForMonth(movement.getIdSaving(),month);
			if (objFluxMovement.count().block().longValue()+1>intNumberMovMax) {
				log.info("No esta permitido realizar mas movimientos en su cuenta de ahorros");
			}else{
				saving = movementRepo.save(movement);
			}
		}
		return saving;
	}

	@Override
	public Flux<Movement> findById(Integer id) {
		return movementRepo.findAll().filter(x -> x.getIdMovementFixed().equals(id));
	}
	
	@Override
	public Flux<AccountSaving> findByIdClient(String id) {
		return accSavingRepo.findAll().filter(x -> x.getIdClient().equals(id));
	}	

	@Override
	public Mono<Movement> update(Movement movement) {
		return movementRepo.save(movement);
	}

	@Override
	public Mono<Void> delete(Movement movement) {
		return movementRepo.delete(movement);
	}
	
	@Override
	public Flux<Movement> findByIdSaving(Integer id) {
		return movementRepo.findAll().filter(x -> x.getIdSaving().equals(id));
	}

	@Override
	public Flux<Movement> findByIdFixed(Integer id) {
		return movementRepo.findAll().filter(x -> x.getIdFixed().equals(id));
	}
	
	@Override
	public Flux<Movement> findByIdCredit(Integer id) {
		return movementRepo.findAll().filter(x -> x.getIdCredit().equals(id));
	}
	
	@Override
	public Flux<Movement> findByIdSavingForMonth(Integer id, Integer month) {
		return movementRepo.findAll().filter(x -> x.getIdSaving().equals(id) && x.getDateMovement().getMonthValue()==month);
	}


}
