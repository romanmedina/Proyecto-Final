package pe.com.nttdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.nttdata.model.Employee;

@Repository
public interface IEmployeeRepositoryPostgres extends JpaRepository<Employee, Integer>{

}
