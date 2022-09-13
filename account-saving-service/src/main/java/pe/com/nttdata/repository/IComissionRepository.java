package pe.com.nttdata.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import pe.com.nttdata.model.Comission;

public interface IComissionRepository extends ReactiveCrudRepository<Comission, Integer>{

}
