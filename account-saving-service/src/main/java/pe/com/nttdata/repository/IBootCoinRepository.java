package pe.com.nttdata.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import pe.com.nttdata.model.BootCoin;

public interface IBootCoinRepository extends ReactiveCrudRepository<BootCoin, Integer> {

}
