package pe.com.nttdata.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.nttdata.model.Holder;
import pe.com.nttdata.serviceimpl.RedisRepositoryImpl;

@RestController
@RequestMapping("/api/v1/accounts/holder")
public class RedisEmployeeController {
	private RedisRepositoryImpl redisRepoImpl;
	
	public RedisEmployeeController(RedisRepositoryImpl redisRepository) {
		this.redisRepoImpl = redisRepository;
	}
	
    @GetMapping
    public Map<String, Holder> findAll(){
        return redisRepoImpl.findAll();
    }

    @GetMapping("/{id}")
    public Holder findById(@PathVariable String id){
        return redisRepoImpl.findById(id);
    }

    @PostMapping
    public void createEmployee(@RequestBody Holder employee){
    	redisRepoImpl.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteAfiliado(@PathVariable String id){
    	redisRepoImpl.delete(id);
    }
}
