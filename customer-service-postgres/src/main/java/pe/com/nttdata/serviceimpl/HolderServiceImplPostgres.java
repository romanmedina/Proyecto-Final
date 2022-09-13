package pe.com.nttdata.serviceimpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.nttdata.model.Holder;
import pe.com.nttdata.repository.IHolderRepositoryPostgres;
import pe.com.nttdata.service.IHolderServicePostgres;


/*
 * Service implement del CRUD para titular
 * Crear Titular
 * Actualizar Titular
 * Listar Titular
 * Listar Por Id el Titular
 * Eliminar Titular
 */
@Service
public class HolderServiceImplPostgres implements IHolderServicePostgres{

	@Autowired
	IHolderRepositoryPostgres holderRepo;

	@Override
	public Holder create(Holder obj) {
		return holderRepo.save(obj);
	}

	@Override
	public Holder update(Holder obj) {
		return holderRepo.save(obj);
	}

	@Override
	public List<Holder> findAll() {
		return holderRepo.findAll();
	}

	@Override
	public Holder findById(Integer id) {
		Optional<Holder> ho = holderRepo.findById(id);
		return ho.isPresent() ? ho.get() : new Holder();
	}

	@Override
	public boolean delete(Integer id) {
		holderRepo.deleteById(id);
		return true;
	}

}
