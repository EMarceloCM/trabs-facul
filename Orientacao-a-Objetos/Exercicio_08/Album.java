package Classes.Exercicio_08;

import java.util.List;
import java.util.ArrayList;

public class Album implements Cloneable{
    private String titulo;
    private List<Musica> musicas;

    public Album(String titulo) {
        this.titulo = titulo;
        this.musicas = new ArrayList<Musica>();
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
    }
    
    public List<Musica> getMusicas() {
        return this.musicas;
    }

    public int getNumeroDeMusicas() {
        return this.musicas.size();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Álbum: ").append(titulo).append("\n");
        sb.append("Músicas: \r\n");
        for (Musica m : musicas) {
            sb.append("- ").append(m.getTitulo()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Album clone() throws CloneNotSupportedException {
        Album albumClone = (Album) super.clone();
        List<Musica> musicasClone = new ArrayList<>();
        for (Musica musica : musicas) {
            musicasClone.add(musica.clone());
        }
        albumClone.musicas = musicasClone;
        return albumClone;
    }
}
