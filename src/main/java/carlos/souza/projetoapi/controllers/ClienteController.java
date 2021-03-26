package carlos.souza.projetoapi.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import carlos.souza.projetoapi.module.Cliente;
import carlos.souza.projetoapi.repositories.ClienteRepository;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@PostMapping(path = "/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save(@RequestBody @Valid Cliente cliente) {
		
		return clienteRepository.save(new Cliente(cliente.getNome(), cliente.getCpf()));		
	}
	
	@GetMapping(path = "/search/id={id}")
	public Optional<Cliente> search(@PathVariable(name = "id") int id) {
		
		return clienteRepository.findById(id);		
	}
	
	@GetMapping("/list")
	public Iterable<Cliente> list(){
		
		return clienteRepository.findAll();
	}
	
	@PutMapping("/update/id={id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable(name="id") Integer id,@RequestBody Cliente cliente){
		
		clienteRepository.findById(id)
			.map( c -> {
				c.setCpf(cliente.getCpf());
				c.setNome(cliente.getNome());
				return clienteRepository.save(cliente);
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/delete/id={id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable(name="id") Integer id){
		
		clienteRepository.findById(id)
			.map( cliente -> {
				clienteRepository.delete(cliente);
				return Void.TYPE;
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
