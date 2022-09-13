package pe.com.nttdata.serviceimpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.nttdata.model.Person;
import pe.com.nttdata.repository.IPersonRepositoryPostgres;
import pe.com.nttdata.service.IPersonServicePostgres;

@Service
public class PersonServiceImplPostgres implements IPersonServicePostgres{

	@Autowired
	IPersonRepositoryPostgres personRepo;
	
	@Override
	public Person create(Person obj) {
		return personRepo.save(obj);
	}

	@Override
	public Person update(Person obj) {
		return personRepo.save(obj);
	}

	@Override
	public List<Person> findAll() {
		return personRepo.findAll();
	}

	@Override
	public Person findById(Integer id) {
		Optional<Person> pe = personRepo.findById(id);
		return pe.isPresent() ? pe.get() : new Person();
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}


}
