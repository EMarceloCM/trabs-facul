package Classes.Exercicio_08;

import java.util.List;
import java.util.ArrayList;

public class Artista implements Cloneable{
    private String nome;
    private List<Album> albuns;

    public Artista(String nome) {
        this.nome = nome;
        this.albuns = new ArrayList<Album>();
    }

    public String getNome() {
        return this.nome;
    }
    
    public List<Album> getAlbuns() {
        return this.albuns;
    }

    public void adicionarAlbum(Album album) {
        this.albuns.add(album);
    }

    public int getNumeroDeAlbuns() {
        return this.albuns.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Artista: ").append(nome).append("\n");
        sb.append("Lista de Albuns:\n");
        for (Album album : albuns) {
            sb.append(album.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Artista clone() throws CloneNotSupportedException {
        Artista artistaClone = (Artista) super.clone();
        List<Album> albunsClone = new ArrayList<>();
        for (Album album : albuns) {
            albunsClone.add(album.clone());
        }
        artistaClone.albuns = albunsClone;
        return artistaClone;
    }
}
