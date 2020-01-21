package com.example.bar.model;

public class Bar {

    private String nome;
    private String endereco;
    private String endereçoImagem;
    private Integer id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereçoImagem() {
        return endereçoImagem;
    }

    public void setEndereçoImagem(String endereçoImagem) {
        this.endereçoImagem = endereçoImagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bar(String nome, String endereco, String endereçoImagem, Integer id) {
        this.nome = nome;
        this.endereco = endereco;
        this.endereçoImagem = endereçoImagem;
        this.id = id;
    }

    public Bar() {

    }
}
