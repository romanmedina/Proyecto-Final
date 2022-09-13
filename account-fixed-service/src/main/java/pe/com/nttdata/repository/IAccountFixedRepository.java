package pe.com.nttdata.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.com.nttdata.model.AccountFixed;

public interface IAccountFixedRepository extends ReactiveCrudRepository<AccountFixed, Integer>{

}
