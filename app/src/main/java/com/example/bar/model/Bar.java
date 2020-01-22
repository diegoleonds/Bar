package com.example.bar.model;

public class Bar {

    private String nome;
    private String endereco;
    private String enderecoImagem;
    private Integer id;

    public Bar(String enderecoImagem) {
        this.enderecoImagem = enderecoImagem;

    }

    public Bar(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public Bar() {

    }

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

    public String getEnderecoImagem() {
        return enderecoImagem;
    }

    public void setEnderecoImagem(String enderecoImagem) {
        this.enderecoImagem = enderecoImagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




}
