package br.com.codenation.repositories;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.models.Time;

import java.util.ArrayList;
import java.util.List;

public class TimeRepositorio {
    List<Time> times = new ArrayList<>();

    public boolean verificaTime(Long idTime){
        for(int i=0; i<times.size();i++){
            if(times.get(i).getId().equals(idTime))
                return true;
        }
        return false;
    }

    public void adicionarTime( Time time){
        if (!verificaTime(time.getId())){
            times.add(time);
        }else {
            throw new IdentificadorUtilizadoException();
        }

    }
    public Time retornaTime(Long idTime){

        return times.stream().filter(time -> time.getId().equals(idTime)).findFirst().orElseThrow(TimeNaoEncontradoException::new);
    }
    public List<Time> retornaTimes(){
        return times;
    }

}
