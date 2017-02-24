package br.com.caelum.agiletickets.models;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import junit.framework.Assert;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void criaUmaSessao() throws Exception {
		
		Espetaculo ivete = new Espetaculo();
		LocalDate inicio = new LocalDate(2017,2,23);
		LocalDate fim = new LocalDate(2017,2,23);
		LocalTime horario = new LocalTime(9,0);
		
		List<Sessao> lista = ivete.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(1, lista.size());
		Sessao sessao = lista.get(0);
		DateTime dataComHorario = inicio.toDateTime(horario);
		Assert.assertEquals(dataComHorario, sessao.getInicio());
		
	}
	
	@Test
	public void criar3SessoesDiarias() throws Exception {
		Espetaculo iron = new Espetaculo();
		LocalDate inicio = new LocalDate(2017,2,23);
		LocalDate fim = new LocalDate(2017,2,25);
		LocalTime horario = new LocalTime(9,0);
		List<Sessao> lista = iron.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		Assert.assertEquals(3, lista.size());
	}	
	
	@Test
	public void criaSessaoSemanal() throws Exception {
		
		Espetaculo jogoPalmeiras = new Espetaculo();
		LocalDate inicio = new LocalDate(2017,1,9);
		LocalDate fim = new LocalDate(2017,1,23);
		LocalTime horario = new LocalTime(17,0);
		
		List<Sessao> lista = jogoPalmeiras.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		Assert.assertEquals(3, lista.size());
	}
}
