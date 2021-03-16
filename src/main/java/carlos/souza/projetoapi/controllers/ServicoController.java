package carlos.souza.projetoapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import carlos.souza.projetoapi.module.Servico;
import carlos.souza.projetoapi.repositories.ServicoRepository;

@RestController
@RequestMapping("/api/servico")
public class ServicoController {

	private ServicoRepository servicoRepository;
	
	@Autowired
	public void ServicoRepository(ServicoRepository servicoRepository) {
		this.servicoRepository = servicoRepository;
	}
	
	@GetMapping("/teste")
	public String teste() {
		return "Funcionou a rota servico";
	}
	
	@PostMapping("/save")
	public Servico save(@RequestParam String descricao, @RequestParam double preco) {
		
		Servico servico = servicoRepository.save(new Servico(descricao, preco));
		
		return servico;		
	}
}
