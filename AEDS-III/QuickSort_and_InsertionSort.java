import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class teste2 {

    // Método para imprimir um vetor
    public static void imprimeVetor(int vetor[], int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println();
    }

    // Método para trocar dois elementos de um vetor
    public static void troca(int vetor[], int i, int j) {
        int aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
    }

    // Método que retorna a mediana de três elementos de um vetor
    public static int medianaDeTres(int vetor[], int esq, int dir) {
        int meio = (esq + dir) / 2;
        if (vetor[esq] > vetor[meio]) {
            troca(vetor, esq, meio);
        }
        if (vetor[esq] > vetor[dir]) {
            troca(vetor, esq, dir);
        }
        if (vetor[meio] > vetor[dir]) {
            troca(vetor, meio, dir);
        }
        return meio;
    }

    // Método para escolher o pivô para o Quick Sort
    public static int escolhePivo(int vetor[], int esq, int dir, int k) {
        if (k == 0) {
            return esq;
        } else if (k == 1) {
            return dir;
        } else if (k == 2) {
            return (esq + dir) / 2;
        } else if (k == 3) {
            return medianaDeTres(vetor, esq, dir);
        } else {
            Random rand = new Random();
            return rand.nextInt(dir - esq + 1) + esq;
        }
    }    

    // Método para ordenar um vetor usando Insertion Sort
    public static void insertionSort(int vetor[], int esq, int dir, int[] resultados) {
        int comp = 0;
        int trocas = 0;
        int acessos = 0;
        int pivos = 0;
        long tempoInicial, tempoFinal, tempoExecucao;
        tempoInicial = System.currentTimeMillis();
        for (int i = esq + 1; i <= dir; i++) {
            int x = vetor[i];
            int j = i - 1;
            while (j >= esq && vetor[j] > x) {
                comp++;
                acessos += 2;
                vetor[j + 1] = vetor[j];
                trocas++;
                j--;
            }
            acessos += 2;
            vetor[j + 1] = x;
            trocas++;
        }
        tempoFinal = System.currentTimeMillis();
        tempoExecucao = tempoFinal - tempoInicial;
        resultados[0] += comp;
        resultados[1] += trocas;
        resultados[2] += acessos;
        resultados[3] += pivos;
        resultados[4] += tempoExecucao;
    }

    public static int particiona(int vetor[], int esq, int dir, int k, int[] resultados) {
        int pivo = vetor[dir];
        int i = esq - 1;
        for (int j = esq; j < dir; j++) {
            if (vetor[j] < pivo) {
                i++;
                int temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
            }
            resultados[0]++; // contagem de comparações
            resultados[1]++; // contagem de trocas
            resultados[2] += 2; // contagem de acessos (vetor[i] e vetor[j])
        }
        int temp = vetor[i + 1];
        vetor[i + 1] = vetor[dir];
        vetor[dir] = temp;
        resultados[1]++; // contagem de trocas
        resultados[2] += 2; // contagem de acessos (vetor[i + 1] e vetor[dir])
        resultados[3]++; // contagem de pivôs
        return i + 1;
    }
    
    public static void ordenaVetorQuickSort(int vetor[], int esq, int dir, int k, int[] resultados) {
        long tempoInicial, tempoFinal, tempoExecucao;
        tempoInicial = System.currentTimeMillis();
        if (esq < dir) {
            int p = particiona(vetor, esq, dir, k, resultados);
            ordenaVetorQuickSort(vetor, esq, p - 1, k, resultados);
            ordenaVetorQuickSort(vetor, p + 1, dir, k, resultados);
        }
        tempoFinal = System.currentTimeMillis();
        tempoExecucao = tempoFinal - tempoInicial;
        resultados[4] += tempoExecucao; // contagem do tempo de execução
    }
    
    public static void quickSort(int vetor[], int esq, int dir, int k, int[] resultados) {
        if (esq < dir) {
            int indicePivo = particiona(vetor, esq, dir, k, resultados);
            quickSort(vetor, esq, indicePivo - 1, k, resultados);
            quickSort(vetor, indicePivo + 1, dir, k, resultados);
        }
    } 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("\r\nEntre com o tamanho do vetor:");
        int n = sc.nextInt();

        int[] vetor = new int[n];

        System.out.println("Escolha o tipo de preenchimento do vetor:");
        System.out.println("1 - Valores aleatórios");
        System.out.println("2 - Valores em ordem crescente");
        System.out.println("3 - Valores em ordem decrescente\r\n");
        int opcao = sc.nextInt();

        if (opcao == 1) {
            for (int i = 0; i < n; i++) {
                vetor[i] = random.nextInt(n);
            }
        } else if (opcao == 2) {
            for (int i = 0; i < n; i++) {
                vetor[i] = i;
            }
        } else if (opcao == 3) {
            for (int i = 0; i < n; i++) {
                vetor[i] = n - i;
            }
        } else {
            System.out.println("Opção inválida.");
            System.exit(0);
        }

        System.out.println("\r\nEscolha o valor de k:");
        System.out.println("0 - Primeiro elemento como pivô");
        System.out.println("1 - Último elemento como pivô");
        System.out.println("2 - Elemento do meio como pivô");
        System.out.println("3 - Mediana de três como pivô");
        System. out.println("4 - Mediana de cinco como pivô\r\n");
        
        int k = sc.nextInt();
        int[] resultadosQuickSort = new int[5];
        int[] resultadosInsertionSort = new int[5];

        System.out.println("\r\nVetor original:");
        for (int i = 0; i < n; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println();

        System.out.println("\r\nVetor ordenado com Quick Sort:");
        ordenaVetorQuickSort(vetor, 0, n - 1, k, resultadosQuickSort);
        for (int i = 0; i < n; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println();
        System.out.println("\r\nNúmero de comparações: " + resultadosQuickSort[0]);
        System.out.println("Número de trocas: " + resultadosQuickSort[1]);
        System.out.println("Número de acessos: " + resultadosQuickSort[2]);
        System.out.println("Número de pivôs: " + resultadosQuickSort[3]);
        System.out.println("Tempo de execução (ms): " + resultadosQuickSort[4]);

        System.out.println();

        System.out.println("\r\nVetor ordenado com Insertion Sort:");
        insertionSort(vetor, 0, n - 1, resultadosInsertionSort);
        for (int i = 0; i < n; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println();
        System.out.println("\r\nNúmero de comparações: " + resultadosInsertionSort[0]);
        System.out.println("Número de trocas: " + resultadosInsertionSort[1]);
        System.out.println("Número de acessos: " + resultadosInsertionSort[2]);
        System.out.println("Número de pivôs: " + resultadosInsertionSort[3]);
        System.out.println("Tempo de execução (ms): " + resultadosInsertionSort[4]);
    }
}
