package ingresso;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;
import junit.framework.Assert;

public class SessaoTest {

	private Sala sala;
	private Filme filme;
	private Sessao sessao;
	private Ingresso ingresso;
	private Lugar a1;
	private Lugar a2;
	private Lugar a3;
	
	
	@Before
	public void requisitosParaOsTestesRodarem() {
		
		sala = new Sala("Eldorado - IMax", new BigDecimal("22.5"));
		filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.0"));
		sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
	
	}
	
	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFilme() {
				
		BigDecimal somaDosPrecosDaSalaEFilme = sala.getPreco().add(filme.getPreco());
		
		
		Assert.assertEquals(somaDosPrecosDaSalaEFilme, sessao.getPreco());
		
	}
	
	@Test
	public void garantequeOLugarA1EstaOcupadoEOsLugaresA2EA3Disponiveis() {
		
		a1 = new Lugar("A", 1);
		a2 = new Lugar("A", 2);
		a3 = new Lugar("A", 3);
		
		ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, a1);
		
		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());
		
		sessao.setIngressos(ingressos);
		
		Assert.assertFalse(sessao.isDisponivel(a1));
		Assert.assertTrue(sessao.isDisponivel(a2));
		Assert.assertTrue(sessao.isDisponivel(a3));
		
	}
}
