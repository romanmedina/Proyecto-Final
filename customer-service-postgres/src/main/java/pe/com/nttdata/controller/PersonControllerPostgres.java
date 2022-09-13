package pe.com.nttdata.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.nttdata.model.Person;
import pe.com.nttdata.service.IPersonServicePostgres;

@RestController
@RequestMapping("/api/v1/person")
public class PersonControllerPostgres {

	@Autowired
	private IPersonServicePostgres personaService;
	
	@GetMapping
    public ResponseEntity<List<Person>> getAll(){
        List<Person> list = personaService.findAll();
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }
	
    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable("id") Integer id){
    	Person persona = personaService.findById(id);
        return new ResponseEntity<Person>(persona, HttpStatus.OK);
    }
	
    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person){
    	Person h = personaService.create(person);
        return new ResponseEntity<Person>(h, HttpStatus.CREATED);
    }
    
}
