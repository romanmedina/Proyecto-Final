package pe.com.nttdata.serviceimpl;

import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pe.com.nttdata.model.Holder;
import pe.com.nttdata.repository.IRedisRepository;

@Repository
public class RedisRepositoryImpl implements IRedisRepository{

	private static final String KEY = "holder";
	private RedisTemplate<String, Holder> redisTemplate;
	private HashOperations hashOperations;
	
	public RedisRepositoryImpl(RedisTemplate<String, Holder> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}
	
	@Override
	public Map<String, Holder> findAll() {
		return hashOperations.entries(KEY);
	}
	@Override
	public Holder findById(String id) {
		return (Holder)hashOperations.get(KEY, id);
	}
	@Override
	public void save(Holder employee) {
		hashOperations.put(KEY, UUID.randomUUID().toString(), employee);
	}
	@Override
	public void delete(String id) {
		hashOperations.delete(KEY, UUID.randomUUID().toString(), id);
	}
}
