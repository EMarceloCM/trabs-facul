package Classes.Exercicio_08;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorMusicas {
	static List<Artista> artistas = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
	static boolean isAdded = false; //controle de cadastro e pesquisa, sempre falso por padrao

	public static void addArtista() {
		System.out.print("Informe o nome do Artista:  ");
		String nome = sc.nextLine();
        
        findArtista(nome);
		if (!isAdded){
			artistas.add(new Artista(nome));
			System.out.println("\r\n-> Artista Cadastrado com Sucesso!\r\n");
		}else{
            System.out.println("Artista já existente!\r\n");
            isAdded = false;
        }
	}

	public static void addAlbum() {
		System.out.print("Informe o artista criador do album:  ");
        String artName = sc.nextLine();

		for (Artista artista : artistas) {
		    if (artista.getNome().equalsIgnoreCase(artName)) {
                System.out.print("Informe o titulo do album:  ");
                artista.adicionarAlbum(new Album(sc.nextLine()));          
                System.out.println("\r\n-> Album cadastrado e vinculado a " + artista.getNome() + "\r\n");
                isAdded = true;
                break;
		    }
		}
		if (!isAdded) {
		    System.out.println("\r\nArtista nao encontrado :(\r\n");
		}else isAdded = false;
	}

	public static void addMusica() {
		System.out.print("Informe o album da musica:  ");
        String albumName = sc.nextLine();

		for (Artista artista : artistas) {
			for (Album album : artista.getAlbuns()) {
				if (album.getTitulo().equalsIgnoreCase(albumName)) {
					System.out.print("Informe o nome da musica:  ");
					String musicName = sc.nextLine();
					System.out.print("Informe a duracao em segundos:  ");
					int duracao = sc.nextInt();
					album.adicionarMusica(new Musica(musicName, duracao));
					System.out.println("\r\n-> Musica " + musicName + " cadastrada no album " + album.getTitulo() + "! \r\n");
					isAdded = true;
					break;
			    }
			}
			if (isAdded) break;
		}
		if (!isAdded) {
		    System.out.println("\r\nAlbum nao encontrada :(\r\n");
		}else isAdded = false;
	}

	public static Artista encontrarArtistaComMaisAlbuns() {
		Artista artistaComMaisAlbuns = null;
		int maxNumeroDeAlbuns = 0;

		for (Artista artista : artistas) {
		    if (artista.getNumeroDeAlbuns() > maxNumeroDeAlbuns) {
                maxNumeroDeAlbuns = artista.getNumeroDeAlbuns();
                artistaComMaisAlbuns = artista;
		    }
		}
        if (artistaComMaisAlbuns == null)
            System.out.println("\r\nNenhum artista ou album cadastrado!\r\n");
        else
		    System.out.println("O artista com mais albuns eh: " + artistaComMaisAlbuns.getNome() + " com " + maxNumeroDeAlbuns + " albuns.\r\n");
        return artistaComMaisAlbuns;
	}

	public static Album encontrarAlbumComMaisMusicas() {
        Album albumComMaisMusicas = null;
        int maxNumeroDeMusicas = 0;

		for (Artista artista : artistas) {
		    for (Album album : artista.getAlbuns()) {
                if (album.getNumeroDeMusicas() > maxNumeroDeMusicas) {
                    maxNumeroDeMusicas = album.getNumeroDeMusicas();
                    albumComMaisMusicas = album;
                }
        	}
		}
        if (albumComMaisMusicas == null)
            System.out.println("\r\nNenhum album ou musica cadastrado!\r\n");
		else
            System.out.println("O album com mais musicas eh: " + albumComMaisMusicas.getTitulo() + " com " + maxNumeroDeMusicas + " musicas.\r\n");
        return albumComMaisMusicas;
    }

	public static void findArtista() {
		System.out.print("Informe o nome do artista:  ");
		String nome = sc.nextLine();
        for (Artista artista : artistas) {
            if (artista.getNome().equalsIgnoreCase(nome)) {
                System.out.println(artista.toString());
                isAdded = true;
                break;
            }
        }
		if (!isAdded) System.out.println("\r\nArtista nao encontrado :(\r\n");
		else isAdded = false;
	}

    private static void findArtista(String a) {
        for (Artista artista : artistas) {
            if (artista.getNome().equalsIgnoreCase(a)) {
                isAdded = true;
                break;
            }
        }
    }

	public static void findAlbum() {
		System.out.print("Informe o nome do album:  ");
		String albumName = sc.nextLine();
		for (Artista artista : artistas) {
		    for (Album album : artista.getAlbuns()) {
                if (album.getTitulo().equalsIgnoreCase(albumName)) {
                    System.out.println(album.toString());
                    isAdded = true;
                    break;
                }
                if (isAdded) break;
		    }
		    if (isAdded) break;
		}
		if (!isAdded) System.out.println("\r\nAlbum nao encontrado :(\r\n");
		else isAdded = false;
	}

	public static void findMusica() {
		System.out.print("Informe o nome da musica:  ");
		String musicName = sc.nextLine();
        for (Artista artista : artistas) {
            for (Album album : artista.getAlbuns()) {
                for (Musica musica : album.getMusicas()) {
                    if (musica.getTitulo().equalsIgnoreCase(musicName)) {
                        System.out.println(musica.toString());
                        isAdded = true;
                        break;
                    }
                }
                if (isAdded) break;
            }
            if (isAdded) break;
        }
		if (!isAdded) System.out.println("\r\nMusica nao encontrada :(\r\n");
		else isAdded = false;
	}

	public static void listarArtistas() {
		if (artistas.size() > 0) {
			for (Artista artista : artistas) {
				System.out.println(artista.toString());
			}
		} else System.out.println("Nenhum artista encontrado!");
	}

	public static void listarAlbuns() {
		int numAlbuns = 0;
		for (Artista artista : artistas) {
			for (Album album : artista.getAlbuns()) {
				System.out.println(album.toString());
				numAlbuns ++;
			}
		}
		if (numAlbuns == 0) System.out.println("Nenhum album encontrado!");
	}

	public static void listarMusicas() {
		int numMusicas = 0;
		for (Artista artista : artistas) {
            for (Album album : artista.getAlbuns()) {
        		for (Musica musica : album.getMusicas()) {
					System.out.println(musica.toString());
					numMusicas++;
				}
        	}
        }
		if (numMusicas == 0) System.out.println("Nenhuma musica encontada!");
	}
}
