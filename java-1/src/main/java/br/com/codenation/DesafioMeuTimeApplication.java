package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;
import br.com.codenation.model.Jogador;
import br.com.codenation.model.Time;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DesafioMeuTimeApplication implements MeuTimeInterface {

	List<Time> times = new ArrayList<>();
	List<Jogador> jogadores = new ArrayList<>();

	public Time buscaTimePeloId(Long timeId) {
		return times.stream().filter(time -> time.getId().equals(timeId)).findAny().orElse(null);
	}

	public Jogador buscaJogadorPeloId(Long jogadorId) {
		return jogadores.stream().filter(jogador -> jogador.getId().equals(jogadorId)).findAny().orElse(null);
	}

	public List<Jogador> buscaJogadoresPeloTime(Long timeId) {
		return jogadores.stream().filter(jogador -> jogador.getIdTime().equals(timeId)).collect(Collectors.toList());
	}

	// ------
	//@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

		if (id == null && nome == null && dataCriacao == null && corUniformePrincipal == null
				&& corUniformeSecundario == null)
			throw new NullPointerException("Não pode ser Null");

		if (buscaTimePeloId(id) != null)
			throw new IdentificadorUtilizadoException();

		times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	//@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (id == null && idTime == null && nome.trim().isEmpty() && dataNascimento == null && nivelHabilidade == null
				&& salario == null)
			throw new NullPointerException("Não pode ser Null");

		if (buscaJogadorPeloId(id) != null)
			throw new IdentificadorUtilizadoException();

		if (buscaTimePeloId(idTime) == null)
			throw new TimeNaoEncontradoException();

		if (nivelHabilidade < 0 || nivelHabilidade > 100)
			throw new IllegalArgumentException("Habilidade - de 0 à 100");

		jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	//@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {

		Jogador cap = buscaJogadorPeloId(idJogador);
		if (cap == null && idJogador == null)
			throw new JogadorNaoEncontradoException();

		Time time = buscaTimePeloId(cap.getId());
		time.setCapitao(cap);
	}

	//@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = buscaTimePeloId(idTime);

		if (time == null && idTime == null)
			throw new TimeNaoEncontradoException();

		if (time.getCapitao() == null)
			throw new CapitaoNaoInformadoException();

		return time.getCapitao().getId();
	}

	//@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		Jogador jogador = buscaJogadorPeloId(idJogador);

		if (jogador == null && idJogador == null)
			throw new JogadorNaoEncontradoException();

		return jogador.getNome();
	}

	//@Desafio("buscarNomeJogador")
	public String buscarNomeTime(Long idTime) {
		Time time = buscaTimePeloId(idTime);

		if (time == null && idTime == null)
			throw new TimeNaoEncontradoException();

		return time.getNome();
	}

	//@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Time time = buscaTimePeloId(idTime);

		if (time == null && idTime == null)
			throw new TimeNaoEncontradoException();

		List<Jogador> jogadores = buscaJogadoresPeloTime(idTime);

		return jogadores.stream().sorted(Comparator.comparing(Jogador::getId)).map(Jogador::getId)
				.collect(Collectors.toList());

	}

	//@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Time time = buscaTimePeloId(idTime);

		if (time == null && idTime == null)
			throw new TimeNaoEncontradoException();

		List<Jogador> listaJogadores = buscaJogadoresPeloTime(idTime);
		Jogador boleiro = Collections.max(listaJogadores, Comparator.comparingLong(Jogador::getNivelHabilidade));

		return boleiro.getId();
	}

	//@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Time time = buscaTimePeloId(idTime);

		if (time == null && idTime == null)
			throw new TimeNaoEncontradoException();

		List<Jogador> ancioes = buscaJogadoresPeloTime(idTime);

		return ancioes.stream().sorted(Comparator.comparingLong(Jogador::getId))
				.min(Comparator.comparing(Jogador::getDataNascimento)).get().getId();
	}

	//@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times
				.stream()
				.sorted(Comparator.comparing(Time::getId))
				.map(Time::getId)
				.collect(Collectors.toList());
	}

	//@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Time time = buscaTimePeloId(idTime);

		if (time == null && idTime == null)
			throw new TimeNaoEncontradoException();

		return buscaJogadoresPeloTime(idTime).stream().sorted(Comparator.comparingLong(Jogador::getId))
				.max(Comparator.comparing(Jogador::getSalario)).get().getId();
	}

	//@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Jogador jogador = buscaJogadorPeloId(idJogador);
		if (jogador == null && idJogador == null)
			throw new JogadorNaoEncontradoException();
		return jogador.getSalario();
	}

	//@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		if (top == null)
			throw new NullPointerException();

		return jogadores.stream()
				.sorted(Comparator.comparing(Jogador::getNivelHabilidade)
				.reversed().thenComparing(Jogador::getId))
				.limit(top).map(Jogador::getId)
				.collect(Collectors.toList());
	}

}
