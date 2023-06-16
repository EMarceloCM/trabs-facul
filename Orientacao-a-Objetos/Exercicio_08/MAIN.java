import java.util.Scanner;
import Classes.Exercicio_08.*;

class MAIN //Exercicio 08 Escolhido para demonstrar
{
    private static Scanner sc = new Scanner(System.in);
    private static int menu(){
        int op = 0;
        System.out.println("--------Bem-Vindo--------");
        System.out.println("1 -> Cadastrar Artista");
        System.out.println("2 -> Cadastrar Album");
        System.out.println("3 -> Cadastrar Musica");
        System.out.println("4 -> Artista com Mais Albuns");
        System.out.println("5 -> Album com Mais Musicas");
        System.out.println("6 -> Pesquisar Artista");
        System.out.println("7 -> Pesquisar Album");
        System.out.println("8 -> Pesquisar Musica");
        System.out.println("9 -> Lista de Artistas");
        System.out.println("10 -> Lista de Albuns");
        System.out.println("11 -> Lista de Musicas");
        System.out.println("12 -> Sair");
        System.out.println("-------------------------");
        op = sc.nextInt();
        return op;
    }
    public static void main(String[] args)
    {
        int i;
        do {
            i = menu();
            switch (i) {
                case 1:
                    GerenciadorMusicas.addArtista();
                    break;
                case 2:
                    GerenciadorMusicas.addAlbum();
                    break;
                case 3:
                    GerenciadorMusicas.addMusica();
                    break;
                case 4:
                    GerenciadorMusicas.encontrarArtistaComMaisAlbuns();
                    break;
                case 5:
                    GerenciadorMusicas.encontrarAlbumComMaisMusicas();
                    break;
                case 6:
                    GerenciadorMusicas.findArtista();
                    break;
                case 7:
                    GerenciadorMusicas.findAlbum();
                    break;
                case 8:
                    GerenciadorMusicas.findMusica();
                    break;
                case 9:
                    GerenciadorMusicas.listarArtistas();
                    break;
                case 10:
                    GerenciadorMusicas.listarAlbuns();
                    break;
                case 11:
                    GerenciadorMusicas.listarMusicas();
                    break;  
                case 12:
                    System.out.println("\r\nObrigado por utilizar nosso sistema!");
                    break;
                default:
                    i = 12;
                    System.out.println("\r\nOpcao invalida!\r\n");
                    break;
            }
        } while (i != 12);
        sc.close();
    }
}
