package pe.com.nttdata.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.nttdata.model.BootCoin;
import pe.com.nttdata.repository.IAccountSavingRepository;
import pe.com.nttdata.repository.IBootCoinRepository;
import pe.com.nttdata.service.IBootCoinService;
import reactor.core.publisher.Mono;

@Service
public class BootCoinServiceImpl implements IBootCoinService{

	@Autowired
	IBootCoinRepository bootCoinRepo;
	
	@Autowired
	IAccountSavingRepository accountRepo;
	
	@Override
	public Mono<BootCoin> comprarBootCoin(BootCoin bootCoin) {
		return bootCoinRepo.save(bootCoin);
	}

	@Override
	public Mono<BootCoin> pagarBootCoin(BootCoin bootCoin) {
//		Mono<AccountSaving> acc = accountRepo.findById(bootCoin.getId());
		return bootCoinRepo.save(bootCoin);
	}

}
