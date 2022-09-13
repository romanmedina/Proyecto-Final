package pe.com.restapibank.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import pe.com.nttdata.model.AccountSaving;
import pe.com.nttdata.repository.IAccountSavingRepository;
import pe.com.nttdata.serviceimpl.AccountSavingImpl;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class AccountSavingImplTest {

	@Mock
	private IAccountSavingRepository iIAccountSavingRepository;
	
	@InjectMocks
	private AccountSavingImpl accountSavingImpl;
	
	@Autowired
	private Mono<AccountSaving> accountSaving;
	
	@BeforeEach
	void ini() {
		accountSaving = Mono.just(new AccountSaving(1,"Cuenta Ahorro Soles","$/.","Bancario","2020202020202020202","2022-09-27T12:26:30.107",5000.99,12.6,"1",2,3,123456789));
	}

	@Test
	void testFindById() {
        Mockito.when(iIAccountSavingRepository.findById(1)).thenReturn(accountSaving);
        Mono<AccountSaving> obj = accountSavingImpl.getById(1);
        assertEquals(accountSaving, obj);
	}

}
