package carlos.souza.projetoapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
