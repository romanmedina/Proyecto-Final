package pe.com.nttdata.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/*
 * Creación de objeto 'employee' con JPA
 */
@Data
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEmployee;
	@Column(name = "dateEntry")
	private LocalDateTime dateEntry;
	@Column(name = "userCreation", nullable = false, length = 30)
	private String userCreation;
	@Column(name = "ipCreation", nullable = false, length = 30)
	private String ipCreation;
	@ManyToOne
	@JoinColumn(name = "idPerson", nullable = false, foreignKey = @ForeignKey(name = "fk_employee_person"))
	private Person person;
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idEmployee == null) ? 0 : idEmployee.hashCode());
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
        Employee other = (Employee) obj;
        if (idEmployee == null) {
            if (other.idEmployee != null)
                return false;
        } else if (!idEmployee.equals(other.idEmployee))
            return false;
        return true;
    }
}
