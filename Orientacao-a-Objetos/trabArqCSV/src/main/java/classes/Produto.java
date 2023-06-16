/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

public class Produto implements Cloneable {
    private int id;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(int id, String nome, double preco, int estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }
    
    public Produto() {
        this.id = 0;
        this.nome = "";
        this.preco = 0.0;
        this.estoque = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    
    public String objToCSV() {
        return this.id + ";" + this.nome + ";" + this.preco + ";" + this.estoque + "\r\n";
    }
    
    public void CSVToObj(String csv) {
        String vetor[] = csv.split(";");
        this.id = Integer.parseInt(vetor[0]);
        this.nome = vetor[1];
        this.preco = Double.parseDouble(vetor[2]);
        this.estoque = Integer.parseInt(vetor[3].trim());
    }

    @Override
    public String toString() {
        return "Produto [ \r\n \t id= " + id + ", \r\n \t nome= " + nome + ", \r\n \t preco= " + preco + ", \r\n \t estoque= " + estoque + "\r\n]";
    }

    @Override
    public Produto clone() throws CloneNotSupportedException {
        try {
            return (Produto) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("A classe Produto não suporta a operação de clonagem.", e);
        }
    }
}