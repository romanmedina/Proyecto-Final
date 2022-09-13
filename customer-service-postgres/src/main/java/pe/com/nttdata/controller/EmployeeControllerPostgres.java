package pe.com.nttdata.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.nttdata.model.Employee;
import pe.com.nttdata.model.Person;
import pe.com.nttdata.service.IEmployeeServicePostgres;
import pe.com.nttdata.service.IPersonServicePostgres;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeControllerPostgres {

	@Autowired
	IEmployeeServicePostgres employeeService;
	
	@Autowired
	IPersonServicePostgres personService;
	
	@GetMapping
    public ResponseEntity<List<Employee>> getAll(){
        List<Employee> list = employeeService.findAll();
        return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") Integer id){
    	Employee employee = employeeService.findById(id);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
    	Person pe = personService.findById(employee.getPerson().getIdPerson());
    	employee.setPerson(pe);
    	Employee e = employeeService.create(employee);
        return new ResponseEntity<Employee>(e, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Employee> update(@RequestBody Employee employee){
    	Employee e = employeeService.update(employee);
        return new ResponseEntity<Employee>(e, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id){
    	employeeService.delete(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
