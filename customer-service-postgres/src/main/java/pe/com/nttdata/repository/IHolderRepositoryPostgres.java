package pe.com.nttdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.nttdata.model.Holder;

@Repository
public interface IHolderRepositoryPostgres extends JpaRepository<Holder, Integer>{

}
