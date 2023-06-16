/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

public class Casa implements Cloneable {
    private String endereco;
    private double area;
    private int num_quartos;
    private double preco;

    public Casa(String endereco, double area, int num_quartos, double preco) {
        this.endereco = endereco;
        this.area = area;
        this.num_quartos = num_quartos;
        this.preco = preco;
    }
    
    public Casa() {
        this.endereco = "";
        this.area = 0.0;
        this.num_quartos = 0;
        this.preco = 0.0;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getNum_quartos() {
        return num_quartos;
    }

    public void setNum_quartos(int num_quartos) {
        this.num_quartos = num_quartos;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public String objToCSV() {
        return this.endereco + ";" + this.area + ";" + this.num_quartos + ";" + this.preco + "\n";
    }
    
    public void CSVToObj(String csv) {
        String vetor[] = csv.split(";");
        this.endereco = vetor[0];
        this.area = Double.parseDouble(vetor[1]);
        this.num_quartos = Integer.parseInt(vetor[2]);
        this.preco = Double.parseDouble(vetor[3]);
    }

    @Override
    public String toString() {
        return "Casa [\r\n \t endereco= " + endereco + ",\r\n \t area= " + area + ",\r\n \t num_quartos= " + num_quartos + ",\r\n \t preco=" + preco + "\r\n]";
    }

    @Override
    public Casa clone() throws CloneNotSupportedException {
        try {
            return (Casa) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("A classe Casa não suporta a operação de clonagem.", e);
        }
    }
}