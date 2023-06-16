package Classes.Exercicio_08;

public class Musica implements Cloneable{
    private String titulo;
    private int duracao; //segundos

    public Musica(String titulo, int duracao) {
        this.titulo = titulo;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public int getDuracao() {
        return this.duracao;
    }

    @Override
    public String toString() {
        return "Musica: " + titulo + " (" + duracao + "s)\r\n";
    }

    @Override
    public Musica clone() throws CloneNotSupportedException {
        return (Musica) super.clone();
    }
}
