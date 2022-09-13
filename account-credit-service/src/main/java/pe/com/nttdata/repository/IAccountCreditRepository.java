package pe.com.nttdata.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import pe.com.nttdata.model.AccountCredit;

public interface IAccountCreditRepository extends ReactiveCrudRepository<AccountCredit, Integer>{

}
