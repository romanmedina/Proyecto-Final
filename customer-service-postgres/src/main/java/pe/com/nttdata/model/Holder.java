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
 * Creación de objeto 'holder' con JPA
 */

@Data
@Entity
@Table(name = "holder")
public class Holder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHolder;
	@Column(name = "dateEntry")
	private LocalDateTime dateEntry;
	@Column(name = "userCreation", nullable = false, length = 30)
	private String userCreation;
	@Column(name = "ipCreation", nullable = false, length = 30)
	private String ipCreation;
	@ManyToOne
	@JoinColumn(name = "idPerson", nullable = false, foreignKey = @ForeignKey(name = "fk_holder_person"))
	private Person person;
	
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idHolder == null) ? 0 : idHolder.hashCode());
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
        Holder other = (Holder) obj;
        if (idHolder == null) {
            if (other.idHolder != null)
                return false;
        } else if (!idHolder.equals(other.idHolder))
            return false;
        return true;
    }

}
