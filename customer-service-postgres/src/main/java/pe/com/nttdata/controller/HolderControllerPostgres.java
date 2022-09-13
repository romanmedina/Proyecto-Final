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

import pe.com.nttdata.model.Holder;
import pe.com.nttdata.service.IHolderServicePostgres;

@RestController
@RequestMapping("/api/v1/holder")
public class HolderControllerPostgres {

	@Autowired
	IHolderServicePostgres holderService;
	
	@GetMapping
    public ResponseEntity<List<Holder>> getAll(){
        List<Holder> list = holderService.findAll();
        return new ResponseEntity<List<Holder>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Holder> findById(@PathVariable("id") Integer id){
    	Holder holder = holderService.findById(id);
        return new ResponseEntity<Holder>(holder, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Holder> create(@RequestBody Holder holder){
    	Holder h = holderService.create(holder);
        return new ResponseEntity<Holder>(h, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Holder> update(@RequestBody Holder holder){
    	Holder h = holderService.update(holder);
        return new ResponseEntity<Holder>(h, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id){
    	holderService.delete(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
