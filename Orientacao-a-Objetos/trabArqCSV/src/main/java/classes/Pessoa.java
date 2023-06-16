/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

public class Pessoa implements Cloneable {
    private String nome;
    private int idade;
    private int matricula;

    public Pessoa(String nome, int idade, int matricula) {
        this.nome = nome;
        this.idade = idade;
        this.matricula = matricula;
    }
    
    public Pessoa() {
        this.nome = "";
        this.idade = 0;
        this.matricula = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    public String objToCSV() {
        return this.nome + ";" + this.idade + ";" + this.matricula + "\r\n";
    }
    
    public void CSVToObj(String csv) {
        String vetor[] = csv.split(";");
        this.nome = vetor[0];
        this.idade = Integer.parseInt(vetor[1]);
        this.matricula = Integer.parseInt(vetor[2].trim());
    }

    @Override
    public String toString() {
        return "Pessoa [ \r\n \t nome= " + nome + ", \r\n \t idade= " + idade + ", \r\n \t matricula= " + matricula + "\r\n]";
    }

    @Override
    public Pessoa clone() throws CloneNotSupportedException {
        try {
            return (Pessoa) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("A classe Pessoa não suporta a operação de clonagem.", e);
        }
    }
}