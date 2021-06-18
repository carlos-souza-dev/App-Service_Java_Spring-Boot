package carlos.souza.projetoapi.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class BigDecimalConvert {
	public BigDecimal converter(String preco) {
		if(preco == null) {
			return null;
		}
		
		preco = preco.replace(".","").replace(",", ".");
		return new BigDecimal(preco);
	}
}