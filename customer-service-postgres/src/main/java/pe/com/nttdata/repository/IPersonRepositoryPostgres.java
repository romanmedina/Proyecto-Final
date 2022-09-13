package pe.com.nttdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.nttdata.model.Person;

@Repository
public interface IPersonRepositoryPostgres extends JpaRepository<Person, Integer>{

}
