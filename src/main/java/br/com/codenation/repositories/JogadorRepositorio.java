package br.com.codenation.repositories;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.models.Jogador;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JogadorRepositorio {
    List<Jogador> jogadores = new ArrayList<>();

    public void checkNullvalue(Long id){
        if(id==null)throw new IllegalArgumentException();
    }

    public boolean verificaJogador(Long idJogador){
        for(int i=0; i<jogadores.size();i++){
            if(jogadores.get(i).getId().equals(idJogador))
                return true;
        }
        return false;

    }


    public void adicionarJogador( Jogador jogador){

            jogadores.add(jogador);


    }

    public void definirCapitao(Long idjogador){

        Jogador novoCapitao = jogadores.stream().filter(player->player.getId().equals(idjogador)).findFirst().orElseThrow(JogadorNaoEncontradoException::new);
          jogadores.stream()
                        .filter(jogador ->jogador.getIdTime().equals(novoCapitao.getIdTime()))
                        .map(jogador -> {jogador.setCapitao(false);
                            return jogador;
                        }).collect(Collectors.toList());


        novoCapitao.setCapitao(true);

    }

    public Long searchCapitaoTime(Long idTime){


        Jogador jogador = jogadores.stream()
                .filter(player ->
                        player.getIdTime().equals(idTime)
                                && player.getCapitao().equals(true)).findFirst().orElseThrow(CapitaoNaoInformadoException::new);


        return jogador.getId();
    }

    public Jogador retornaJogador(Long idJogador){

        return jogadores.stream()
                .filter(jogador -> jogador.getId().equals(idJogador))
                .findFirst().orElseThrow(JogadorNaoEncontradoException::new);
    }

    public List<Jogador> retornaJogadoresTime(Long idTime){
        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime()
                        .equals(idTime))
                .collect(Collectors.toList());
    }

    public Jogador retornaMelhorJogador(Long idTime){
         Jogador player = jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getNivelHabilidade))
                 .findFirst().orElseThrow(TimeNaoEncontradoException::new);
return player;


    }

    public Jogador retornaJogadorMaisVelho(Long idTime){
        Jogador player = jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .min(Comparator.comparing(Jogador::getDataNascimento).thenComparing(Jogador::getId))
                .orElseThrow(TimeNaoEncontradoException::new);

        return player;
    }

    public Jogador retornaJogadorMaiorSalario(Long idTime){
        return  jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .sorted(Comparator.comparing(Jogador::getSalario).reversed().thenComparing(Jogador::getId))
                .findFirst().orElseThrow(TimeNaoEncontradoException::new);

    }

    public List<Jogador> retornaJogadores(){
        return jogadores;
    }
}
