package pe.com.nttdata.service;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.com.nttdata.model.Client;

public interface IClientRepository extends ReactiveCrudRepository<Client, Integer>{

}
