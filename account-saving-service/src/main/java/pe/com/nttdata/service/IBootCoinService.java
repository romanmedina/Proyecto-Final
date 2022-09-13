package pe.com.nttdata.service;

import pe.com.nttdata.model.BootCoin;
import reactor.core.publisher.Mono;

public interface IBootCoinService {

	Mono<BootCoin> comprarBootCoin(BootCoin bootCoin);
	Mono<BootCoin> pagarBootCoin(BootCoin bootCoin);
}
