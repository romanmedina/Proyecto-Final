package pe.com.nttdata.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import pe.com.nttdata.client.CustomerClient;
import pe.com.nttdata.client.NotFoundException;
import pe.com.nttdata.model.AccountSaving;
import pe.com.nttdata.model.Client;
import pe.com.nttdata.model.ClientType;
import pe.com.nttdata.repository.IAccountSavingRepository;
import pe.com.nttdata.service.IAccountSavingService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class AccountSavingImpl implements IAccountSavingService {

	@Autowired
	private IAccountSavingRepository iIAccountSavingRepository;

	@Autowired
	private CustomerClient customerClient;

	@Override
	public Flux<AccountSaving> findAll() {
		return iIAccountSavingRepository.findAll();
	}

	@Override
	public Flux<AccountSaving> findById(Integer id) {
		return iIAccountSavingRepository.findAll().filter(x -> x.getIdSaving().equals(id));
	}
	
	@Override
	public Mono<AccountSaving> getById(Integer id) {
		return iIAccountSavingRepository.findById(id)
				.switchIfEmpty(Mono.error(new NotFoundException("Id Saving: " + id + " )")));
	}

	@Override
	public Flux<AccountSaving> findByIdClient(String idClient) {
		return iIAccountSavingRepository.findAll().filter(x -> x.getIdClient().equals(idClient));
	}

	/*Un cliente personal solo puede tener un máximo de una cuenta de ahorro,
	 * una cuenta corriente o cuentas a plazo fijo.
	 */
	@Override
	public Mono<AccountSaving> saveAccSavingByClient(AccountSaving accountSaving, Long cantidad) {
		log.info("*****Ingresó al método: saveAccSavingByClient*****");
		Client customer = customerClient.get(accountSaving.getIdClient()).getBody();
			if (customer.getType().equals(ClientType.PERSONAL)) {
				if (!(cantidad > 0)) {
					log.info("**EXITO: Cuenta de Ahorro/Corriente creada para el cliente de tipo PERSONAL**");
					return iIAccountSavingRepository.save(accountSaving);
				} else {
					log.info("**CREACIÓN FALLIDA: Ya existe una cuenta para el cliente**");
					return null;
				}
			} else {
				log.info("**CREACIÓN FALLIDA: El cliente no es tipo PERSONAL**");
				return null;
			}
	}

	/*PERSONAL_VIP: para solicitar este producto el cliente debe tener una tarjeta 
	 *de crédito con el banco al momento de la creación de la cuenta
	 */
	@Override
	public Mono<AccountSaving> saveAccSavingClientVIP(AccountSaving accountSaving, Long cantidad) {
		Client customer = customerClient.get(accountSaving.getIdClient()).getBody();
		if (customer.getType().equals(ClientType.PERSONAL_VIP)) {
			if (cantidad > 0) {
				log.info("**EXITO: Cuenta de Ahorro creada para el cliente PERSONAL_VIP**");
				return iIAccountSavingRepository.save(accountSaving);
			} else {
				log.info("**CREACIÓN FALLIDA: El cliente no cuenta con una tarjeta de crédito**");
				return null;
			}
		} else {
			log.info("**CREACIÓN FALLIDA: El cliente no es tipo PERSONAL_VIP**");
			return null;
		}
	}

	/*▪ BUSSINESS_PYME El cliente debe tener una tarjeta de crédito con el
	 * banco al momento de la creación de la cuenta.
	 */					
	@Override
	public Mono<AccountSaving> saveAccCurrentBussinessPyme(AccountSaving accountSaving, Long cantidad) {
		Client customer = customerClient.get(accountSaving.getIdClient()).getBody();
		if (customer.getType().equals(ClientType.BUSINESS_PYME)) {
			if (cantidad > 0) {
				log.info("**EXITO: Cuenta Corriente creada para el cliente EMPRESARIAL_PYME**");
				return iIAccountSavingRepository.save(accountSaving);
			} else {
				log.info("**CREACIÓN FALLIDA: El cliente no cuenta con una tarjeta de crédito**");
				return null;
			}
		} else {
			log.info("**CREACIÓN FALLIDA: El cliente no es tipo EMPRESARIAL_PYME**");
			return null;
		}
	}
	
	@Override
	public Mono<AccountSaving> updateAccSaving(AccountSaving accountSaving) {
		return iIAccountSavingRepository.save(accountSaving);
	}
	
	@Override
	public Mono<Void> delete(AccountSaving account_saving) {
		return iIAccountSavingRepository.delete(account_saving);
	}
}
