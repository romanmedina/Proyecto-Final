package pe.com.nttdata.model;

public enum ClientType {
  PERSONAL, BUSINESS, PERSONAL_VIP, BUSINESS_PYME;

  static public boolean isValid(String type) {
    for (ClientType customerType : ClientType.values()) {
      if (customerType.name().equals(type)) {
        return true;
      }
    }
    return false;
  }
}
