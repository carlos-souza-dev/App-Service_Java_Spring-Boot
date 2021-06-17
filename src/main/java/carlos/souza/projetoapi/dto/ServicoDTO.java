package carlos.souza.projetoapi.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ServicoDTO {
	
	private String descricao;
	private String preco;
	private String data;
	private Integer idCliente;	

}
