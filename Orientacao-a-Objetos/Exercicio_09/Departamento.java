package Classes.Exercicio_09;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private List<Funcionario> funcionarios;
    private Gerente gerente;

    public Departamento(Gerente gerente) {
        this.funcionarios = new ArrayList<>();
        this.gerente = gerente;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

    public void removerFuncionario(Funcionario funcionario) {
        this.funcionarios.remove(funcionario);
    }

    // getters e setters
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Departamento{\n");
        sb.append("funcionarios=\n");
        for (Funcionario f : funcionarios) {
            sb.append(f.toString()).append("\n");
        }
        sb.append(", gerente=").append(gerente).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
