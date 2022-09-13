package pe.com.nttdata.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import pe.com.nttdata.model.Movement;

public interface IMovementRepository extends ReactiveCrudRepository<Movement, Integer>{

}
