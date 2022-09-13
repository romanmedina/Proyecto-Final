package pe.com.nttdata.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.nttdata.model.Comission;
import pe.com.nttdata.repository.IComissionRepository;
import pe.com.nttdata.service.IComissionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ComissionServiceImpl implements IComissionService{

	@Autowired 
	IComissionRepository comissionRepo;

	@Override
	public Flux<Comission> getAll() {
		return comissionRepo.findAll();
	}
	
	@Override
	public Flux<Comission> findById(Integer id) {
		return comissionRepo.findAll().filter(x -> x.getIdComission().equals(id));
	}

	@Override
	public Mono<Comission> create(Comission comission) {
		return comissionRepo.save(comission);
	}

	@Override
	public Mono<Comission> update(Comission comission) {
		return comissionRepo.save(comission);
	}

	@Override
	public Mono<Void> delete(Comission comission) {
		return comissionRepo.delete(comission);
	}

	@Override
	public Flux<Comission> findByIdSaving(Integer id) {
		return comissionRepo.findAll().filter(x -> x.getIdSaving().equals(id));
	}
	
}
