package pe.com.nttdata.client;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
