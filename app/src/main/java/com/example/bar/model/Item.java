package com.example.bar.model;

/**
 * Criada para segurar as informações puxadas
 * do banco (ver classe Conexao).
 *
 * os atributos recebem os mesmos valores
 * contidos nas colunas da tabela Item.
 *
 * @author Diego <diego.santos@hbsis.com.br>
 */
public class Item {

    private Integer id, fkProduto, fkBar;
    private double preco;

    public Item(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(Integer fkProduto) {
        this.fkProduto = fkProduto;
    }

    public Integer getFkBar() {
        return fkBar;
    }

    public void setFkBar(Integer fkBar) {
        this.fkBar = fkBar;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
