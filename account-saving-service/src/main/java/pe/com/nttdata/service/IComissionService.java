package pe.com.nttdata.service;

import pe.com.nttdata.model.Comission;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IComissionService {

	Flux<Comission> getAll();
	Flux<Comission> findById(Integer id);
	Mono<Comission> create(Comission comission);
	Mono<Comission> update(Comission comission);
	Mono<Void> delete(Comission comission);
	Flux<Comission> findByIdSaving(Integer id);
}
