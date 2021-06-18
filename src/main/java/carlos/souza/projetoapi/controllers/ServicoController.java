package carlos.souza.projetoapi.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import carlos.souza.projetoapi.dto.ServicoDTO;
import carlos.souza.projetoapi.module.Cliente;
import carlos.souza.projetoapi.module.Servico;
import carlos.souza.projetoapi.repositories.ClienteRepository;
import carlos.souza.projetoapi.repositories.ServicoRepository;
import carlos.souza.projetoapi.util.BigDecimalConvert;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servico")
@RequiredArgsConstructor
public class ServicoController {

	private final ClienteRepository clienteRepository;
	private final ServicoRepository servicoRepository;
	private final BigDecimalConvert decimalConverter;
	
	@GetMapping("/teste")
	public String teste() {
		return "Funcionou a rota servico";
	}
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Servico save(@RequestBody ServicoDTO dto) {
		
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();
			
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> 
					new ResponseStatusException(
							HttpStatus.BAD_REQUEST, "Cliente inexistente!"));
				
		Servico servico = new Servico();
		servico.setDescricao(dto.getDescricao());
		servico.setData(data);
		servico.setCliente(cliente);
		servico.setPreco(decimalConverter.converter(dto.getPreco()));
		
		return servicoRepository.save(servico);
		
	}
	
	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Servico> list() {
		
		return servicoRepository.findAll();
		
	}
	
//	@PutMapping("/update")
//	@ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
//	public Servico update(@RequestBody ServicoDTO dto, int id) {
//		
//		servicoRepository.findById(dto.getIdCliente())
//		.map( c -> {
//			c.setDescricao(dto.getDescricao());
//			c.setId(dto.getIdCliente());
//			c.setPreco(dto.getPreco());
//			
//			servicoRepository.
//			return servicoRepository.save(dto);
//		})
//		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//	}
}
