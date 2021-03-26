package carlos.souza.projetoapi.module;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@NotBlank(message = "Nome é obrigatório")
	@Column(nullable = false, length = 10)
	public String nome;
	
	@NotBlank(message = "CPF é obrigatório")
	@Column(nullable = false, length = 14)
	public String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_cadastro", updatable = false)
	public LocalDate dataCadastro;
	
	public Cliente() {
		
	}
	
	public Cliente(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataCadastro = LocalDate.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	
}
