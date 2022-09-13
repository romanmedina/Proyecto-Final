package pe.com.nttdata.repository;

import java.util.Map;
import pe.com.nttdata.model.Holder;

public interface IRedisRepository {

    Map<String, Holder> findAll();
    Holder findById(String id);
    void save(Holder employee);
    void delete(String id);
}
