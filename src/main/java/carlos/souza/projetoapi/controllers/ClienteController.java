package carlos.souza.projetoapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import carlos.souza.projetoapi.module.Cliente;
import carlos.souza.projetoapi.repositories.ClienteRepository;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping(path = "/teste")
	public List<Cliente> teste() {
		Cliente cli = new Cliente("Carlos", "37638285832");
		Cliente cli2 = new Cliente("Carlos", "37638285832");
		
		List<Cliente> clientes = new ArrayList<>();
		
		clientes.add(cli);
		clientes.add(cli2);
		return clientes;
	}
	
	@PostMapping(path = "/save")
	public Cliente save(@RequestParam String nome, @RequestParam String cpf) {
		
		return clienteRepository.save(new Cliente(nome, cpf));		
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
	public void update(@PathVariable(name="id") Integer id, Cliente cliente){
		
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
