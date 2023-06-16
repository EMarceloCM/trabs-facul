package Classes.Exercicio_14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Universidade implements Cloneable {
    private Map<Curso, List<Estudante>> estudantesPorCurso; //lista do tipo <chave, valorAssociado>

    public Universidade() {
        this.estudantesPorCurso = new HashMap<>();
    }

    public void adicionarEstudante(Estudante estudante, Curso curso) {
        List<Estudante> estudantesDoCurso = this.estudantesPorCurso.get(curso);

        if (estudantesDoCurso == null) {
            estudantesDoCurso = new ArrayList<>();
            this.estudantesPorCurso.put(curso, estudantesDoCurso);
        }

        estudantesDoCurso.add(estudante);
    }

    public void removerEstudante(Estudante estudante, Curso curso) {
        List<Estudante> estudantesDoCurso = this.estudantesPorCurso.get(curso);

        if (estudantesDoCurso != null) {
            estudantesDoCurso.remove(estudante);

            if (estudantesDoCurso.isEmpty()) {
                this.estudantesPorCurso.remove(curso);
            }
        }
    }

    public int quantidadeDeAlunos() {
        int quantidade = 0;

        for (List<Estudante> estudantesDoCurso : this.estudantesPorCurso.values()) {
            quantidade += estudantesDoCurso.size();
        }

        return quantidade;
    }

    public Curso cursoComMaisAlunos() {
        Curso cursoComMaisAlunos = null;
        int maiorQuantidade = 0;

        for (Map.Entry<Curso, List<Estudante>> entry : this.estudantesPorCurso.entrySet()) {
            int quantidadeDeAlunosDoCurso = entry.getValue().size();

            if (quantidadeDeAlunosDoCurso > maiorQuantidade) {
                cursoComMaisAlunos = entry.getKey();
                maiorQuantidade = quantidadeDeAlunosDoCurso;
            }
        }

        return cursoComMaisAlunos;
    }

    public List<Curso> cincoCursosComMaisAlunos() {
        List<Curso> cursosComMaisAlunos = new ArrayList<>(this.estudantesPorCurso.keySet());
        cursosComMaisAlunos.sort((c1, c2) -> {
            int quantidadeDeAlunosC1 = this.estudantesPorCurso.get(c1).size();
            int quantidadeDeAlunosC2 = this.estudantesPorCurso.get(c2).size();

            return Integer.compare(quantidadeDeAlunosC2, quantidadeDeAlunosC1);
        });

        return cursosComMaisAlunos.subList(0, Math.min(cursosComMaisAlunos.size(), 5));
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Curso, List<Estudante>> entry : estudantesPorCurso.entrySet()) {
            sb.append(entry.getKey().toString()).append(": ");
            sb.append(entry.getValue().toString()).append("\n");
        }
        return sb.toString();
    }
    
    @Override
    public Universidade clone() throws CloneNotSupportedException {
        Universidade universidadeClone = (Universidade) super.clone();
        
        // clonando o map de estudantes por curso
        Map<Curso, List<Estudante>> estudantesPorCursoClone = new HashMap<>();
        for (Map.Entry<Curso, List<Estudante>> entry : this.estudantesPorCurso.entrySet()) {
            Curso curso = entry.getKey();
            List<Estudante> estudantes = entry.getValue();
            List<Estudante> estudantesClone = new ArrayList<>();
            
            // clonando a lista de estudantes de cada curso
            for (Estudante estudante : estudantes) {
                estudantesClone.add(estudante.clone());
            }
            
            estudantesPorCursoClone.put(curso.clone(), estudantesClone);
        }
        
        universidadeClone.estudantesPorCurso = estudantesPorCursoClone;
        
        return universidadeClone;
    }
}
