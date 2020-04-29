package br.com.codenation.models;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Jogador {


    private Long id;
    private Long idTime;
    private String nome;
    private LocalDate dataNascimento;

    public void setNivelHabilidade(Integer nivelHabilidade) {
        if(nivelHabilidade< 0 || nivelHabilidade >100){
            throw new IllegalArgumentException("Nível de habilidade deve estar entre 0 e 100");
        }

        this.nivelHabilidade = nivelHabilidade;
    }

    private Integer nivelHabilidade;
    private BigDecimal salario;

    private Boolean capitao;

    public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        if(id==null||idTime==null||nome==null|| dataNascimento==null||nivelHabilidade==null||salario==null)
            throw new IllegalArgumentException("Todos os parâmetros são obrigatórios");

        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        setNivelHabilidade(nivelHabilidade);
        this.salario = salario;
        this.capitao = false;

    }
    public Jogador(){}

    public Long getId() {
        return id;
    }

    public Long getIdTime() {
        return idTime;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public Integer getIdade(){
        Date date = new Date();

        return 0;
    }

    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Boolean getCapitao() {
        return capitao;

    }
    public void setCapitao(Boolean capitao) {
        this.capitao = capitao;
    }
}
