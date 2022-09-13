package pe.com.nttdata.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Holder implements Serializable{

	private static final long serialVersionUID =1L;
	private String fullName;
	private String dni;
	private String email;
	private String phone;
	private LocalDateTime birthDay;
	private Boolean active;
}
