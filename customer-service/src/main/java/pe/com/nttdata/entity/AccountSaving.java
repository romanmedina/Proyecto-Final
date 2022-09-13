package pe.com.nttdata.entity;

public class AccountSaving {

    private String jsonAccountSaving;
    
    public AccountSaving() {
    	
    }
    
    public AccountSaving(String jsonAccountSaving) {
        this.jsonAccountSaving = jsonAccountSaving;
    }
    
    @Override
    public String toString() {
        return "AccountSaving{" +
                "JsonAccountSaving='" + jsonAccountSaving +'\'' +
                '}';
    }
}
