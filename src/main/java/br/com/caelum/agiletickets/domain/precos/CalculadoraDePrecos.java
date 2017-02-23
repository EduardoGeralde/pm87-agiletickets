package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco = sessao.getPreco();
		double percentualDisponivel = (sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue();
		
		
		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			if(percentualDisponivel <= 0.05) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			} 
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			if(percentualDisponivel <= 0.50) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			}
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}