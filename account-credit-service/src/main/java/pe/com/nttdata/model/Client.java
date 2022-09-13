package pe.com.nttdata.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
	private String id;
	private String fullName;
	private String typeDoc;
	private String numberDoc;
	private String email;
	private String phone;
	private Boolean active;
	private ClientType type;
}
