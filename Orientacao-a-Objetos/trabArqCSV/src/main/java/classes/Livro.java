/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

public class Livro implements Cloneable {
    private String titulo;
    private String autor;
    private int num_pag;
    private double preco;

    public Livro(String titulo, String autor, int num_pag, double preco) {
        this.titulo = titulo;
        this.autor = autor;
        this.num_pag = num_pag;
        this.preco = preco;
    }
    
    public Livro() {
        this.titulo = "";
        this.autor = "";
        this.num_pag = 0;
        this.preco = 0.0;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNum_pag() {
        return num_pag;
    }

    public void setNum_pag(int num_pag) {
        this.num_pag = num_pag;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public String objToCSV() {
        return this.titulo + ";" + this.autor + ";" + this.num_pag + ";" + this.preco + "\r\n";
    }
    
    public void CSVToObj(String csv) {
        String vetor[] = csv.split(";");
        this.titulo = vetor[0];
        this.autor = vetor[1];
        this.num_pag = Integer.parseInt(vetor[2]);
        this.preco = Double.parseDouble(vetor[3]);
    }

    @Override
    public String toString() {
        return "Livro [ \r\n \t titulo= " + titulo + ", \r\n \t autor= " + autor + ", \r\n \t num_pag= " + num_pag + ", \r\n \t preco= " + preco + "\r\n]";
    }

    @Override
    public Livro clone() throws CloneNotSupportedException {
        try {
            return (Livro) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("A classe Livro não suporta a operação de clonagem.", e);
        }
    }
}