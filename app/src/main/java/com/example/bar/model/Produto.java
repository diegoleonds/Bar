package com.example.bar.model;

/**
 * Classe criada só para segurar as informações puxadas
 * da tabela produto (ver classe Conexao)
 *
 * @author Diego <diego.santos@hbsis.com.br>
 */


public class Produto {

    private String nome;
    private Integer id, idImagem;

    public Produto(String nome, Integer id, Integer idImagem){

        setNome(nome);
        setId(id);
        setIdImagem(idImagem);
    }

    public Produto(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(Integer idImagem) {
        this.idImagem = idImagem;
    }
}
