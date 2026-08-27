package com.leadfinder.leadfinder.model;

import jakarta.persistence.*;

@Entity
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String telefone;
    private Double avaliacao;
    private String site;

    public Lead(){}

    public Long getId(){ return id; }

    public String getNome(){ return nome; }
    public void setNome(String nome){ this.nome = nome; }

    public String getEndereco(){ return endereco; }
    public void setEndereco(String endereco){ this.endereco = endereco; }

    public String getTelefone(){ return telefone; }
    public void setTelefone(String telefone){ this.telefone = telefone; }

    public Double getAvaliacao(){ return avaliacao; }
    public void setAvaliacao(Double avaliacao){ this.avaliacao = avaliacao; }

    public String getSite(){ return site; }
    public void setSite(String site){ this.site = site; }

}