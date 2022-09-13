package pe.com.nttdata.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pe.com.nttdata.model.Client;

@FeignClient(name = "customer-service", url = "http://localhost:8083", path = "/api/v1/clients", configuration = ClientConfiguration.class)
public interface CustomerClient {
  @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Client> get(@PathVariable String id);
}
