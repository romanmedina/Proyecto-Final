package pe.com.nttdata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "bootcoin")
public class BootCoin{

	@Id
	private Integer id;
	private String typeDocument;
	private String nroDocument;
	private String phone;
	private String email;
	private String typeCurrency;
	private double rate;
	private double balance;
	private String accountNumber;
	
}
