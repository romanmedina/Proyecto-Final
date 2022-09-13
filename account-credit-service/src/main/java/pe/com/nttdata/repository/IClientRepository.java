package pe.com.nttdata.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.com.nttdata.model.Client;

public interface IClientRepository extends ReactiveCrudRepository<Client, Integer>{

}
