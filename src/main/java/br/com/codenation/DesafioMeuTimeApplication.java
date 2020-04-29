package br.com.codenation;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.models.Jogador;
import br.com.codenation.models.Time;
import br.com.codenation.repositories.JogadorRepositorio;
import br.com.codenation.repositories.TimeRepositorio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DesafioMeuTimeApplication  {
	 TimeRepositorio  timeRepositorio = new TimeRepositorio();
	 JogadorRepositorio jogadoresRepositorio = new JogadorRepositorio();




	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario)  {
		Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		timeRepositorio.adicionarTime(time);
	}


	@Desafio("incluirJogador")
	public void incluirJogador(Long id,  Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		Jogador jogador = new Jogador( id,  idTime,  nome,  dataNascimento,  nivelHabilidade,  salario);
		if (jogadoresRepositorio.verificaJogador(id))throw new IdentificadorUtilizadoException();
		if(!timeRepositorio.verificaTime(idTime))throw new TimeNaoEncontradoException();


		jogadoresRepositorio.adicionarJogador(jogador);

	}

	@Desafio("definirCapitao")
	public void definirCapitao( Long idJogador) {
		jogadoresRepositorio.checkNullvalue(idJogador);
		jogadoresRepositorio.definirCapitao(idJogador);
	}


	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime( Long idTime) {
		jogadoresRepositorio.checkNullvalue(idTime);
		if(!timeRepositorio.verificaTime(idTime)) throw new TimeNaoEncontradoException();
		return jogadoresRepositorio.searchCapitaoTime(idTime);

	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador( Long idJogador) {
		jogadoresRepositorio.checkNullvalue(idJogador);
		Jogador jogador = jogadoresRepositorio.retornaJogador(idJogador);
		return  jogador.getNome();

	}


	@Desafio("buscarNomeTime")
	public String buscarNomeTime( Long idTime) {
		jogadoresRepositorio.checkNullvalue(idTime);
		Time time = timeRepositorio.retornaTime(idTime);

		return time.getNome();
	}


	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime( Long idTime) {
		jogadoresRepositorio.checkNullvalue(idTime);
		if(!timeRepositorio.verificaTime(idTime)) throw new TimeNaoEncontradoException();

		return jogadoresRepositorio.retornaJogadoresTime(idTime)
				.stream().sorted(Comparator.comparing(Jogador::getId))
				.map(Jogador::getId)
				.collect(Collectors.toList());

	}


	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime( Long idTime) {
			jogadoresRepositorio.checkNullvalue(idTime);
			return jogadoresRepositorio.retornaMelhorJogador(idTime).getId();

	}


	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho( Long idTime) {
		jogadoresRepositorio.checkNullvalue(idTime);
		return jogadoresRepositorio.retornaJogadorMaisVelho(idTime).getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> times =timeRepositorio.retornaTimes()
				.stream()
				.sorted(Comparator.comparing(Time::getId))
				.map(Time::getId)
				.collect(Collectors.toList());
		return times;
	}


	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario( Long idTime) {
		jogadoresRepositorio.checkNullvalue(idTime);
		return jogadoresRepositorio.retornaJogadorMaiorSalario(idTime).getId();

	}


	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador( Long idJogador) {
		jogadoresRepositorio.checkNullvalue(idJogador);
		return jogadoresRepositorio.retornaJogador(idJogador).getSalario();

	}


	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores( Integer top) {
		if(top==null)throw new IllegalArgumentException();
		return  jogadoresRepositorio.retornaJogadores().stream()
				.sorted(Comparator.comparing(Jogador::getNivelHabilidade)
						.reversed().thenComparing(Jogador::getId))
				.map(Jogador::getId).limit(top).collect(Collectors.toList());



	}



	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora( Long timeDaCasa,  Long timeDeFora) {
		jogadoresRepositorio.checkNullvalue(timeDaCasa);
		jogadoresRepositorio.checkNullvalue(timeDeFora);

		if(!timeRepositorio.verificaTime(timeDaCasa) && !timeRepositorio.verificaTime(timeDeFora))
		{
			throw new TimeNaoEncontradoException();
		}

		Time casa = timeRepositorio.retornaTime(timeDaCasa);
		Time fora = timeRepositorio.retornaTime(timeDeFora);
		String corDaCamisa;
		if(casa.getCorUniformePrincipal().equals(fora.getCorUniformePrincipal())){
			corDaCamisa = fora.getCorUniformeSecundario();

		}else{
			corDaCamisa = fora.getCorUniformePrincipal();
		}

		return corDaCamisa;
	}



}
