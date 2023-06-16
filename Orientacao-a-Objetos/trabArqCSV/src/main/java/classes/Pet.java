/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

public class Pet implements Cloneable {
    private String especie;
    private String raca;
    private int idade;
    private double peso;

    public Pet(String especie, String raca, int idade, double peso) {
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.peso = peso;
    }
    
    public Pet() {
        this.especie = "";
        this.raca = "";
        this.idade = 0;
        this.peso = 0.0;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public String objToCSV() {
        return this.especie + ";" + this.raca + ";" + this.idade + ";" + this.peso + "\r\n";
    }
    
    public void CSVToObj(String csv) {
        String vetor[] = csv.split(";");
        this.especie = vetor[0];
        this.raca = vetor[1];
        this.idade = Integer.parseInt(vetor[2]);
        this.peso = Double.parseDouble(vetor[3]);
    }

    @Override
    public String toString() {
        return "Pet [ \r\n \t especie= " + especie + ", \r\n \t raca= " + raca + ", \r\n \t idade= " + idade + ", \r\n \t peso= " + peso + "\r\n]";
    }

    @Override
    public Pet clone() throws CloneNotSupportedException {
        try {
            return (Pet) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("A classe Pet não suporta a operação de clonagem.", e);
        }
    }
}