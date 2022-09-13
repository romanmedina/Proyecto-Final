package pe.com.nttdata.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "account_fixed")
public class AccountFixed {
	
	@Id
	private Integer idFixed;
	private String typeCurrency;
	private String accountNumber;
	private LocalDateTime depositDate;
	private Double balance;
	private String countMonth;
	private Double interestPay;
	private String idClient;
	private Integer numberMovMax;

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idFixed == null) ? 0 : idFixed.hashCode());
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
        AccountFixed other = (AccountFixed) obj;
        if (idFixed == null) {
            if (other.idFixed != null)
                return false;
        } else if (!idFixed.equals(other.idFixed))
            return false;
        return true;
    }
}
