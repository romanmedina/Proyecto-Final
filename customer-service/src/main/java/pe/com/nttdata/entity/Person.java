package pe.com.nttdata.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Person {

	@Id
	private Integer idPerson;
	private String fullName;
	private String typeDoc;
	private String numberDoc;
	private LocalDateTime dateBirth;
	private String email;
	private String phone;
	private Boolean active;
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPerson == null) ? 0 : idPerson.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (idPerson == null) {
            if (other.idPerson != null)
                return false;
        } else if (!idPerson.equals(other.idPerson))
            return false;
        return true;
    }
	
}
