package pe.com.nttdata.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import pe.com.nttdata.model.AccountSaving;

public interface IAccountSavingRepository extends ReactiveCrudRepository<AccountSaving, Integer>{

}
