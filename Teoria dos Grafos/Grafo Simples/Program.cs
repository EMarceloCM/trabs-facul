using System;
using System.Collections.Generic;
using System.Text;
class Grafo
{
    private readonly int V;
    private List<List<int>> adj;

    public Grafo(int V)
    {
        this.V = V;
        adj = new List<List<int>>(V);

        for (int i = 0; i < V; i++)
        {
            adj.Add([]);

            for (int j = 0; j < V; j++)
            {
                adj[i].Add(0);
                Console.Write($"{adj[i][j]}");
                if (j != (V - 1))
                    Console.Write("-");
            }
            Console.WriteLine("\n");
        }
    }

    public void AdicionarAresta(int v1, int v2)
    {
        adj[v1][v2] = 1;
        adj[v2][v1] = 1;
    }

    public bool Conexo()
    {
        for (int i = 0; i < V; i++)
        {
            for (int j = i+1; j < V; j++)
            {
                if (adj[i][j] != 0) break;
                if (adj[i][j] == 0 && j == V-1) return true;
            }
        }

        return false;
    }

    public bool Completo()
    {
        for (int i = 0; i < V; i++)
        {
            if (adj[i].Count != V - 1)
                return false;
        }
        return true;
    }

    public override string ToString()
    {
        StringBuilder sb = new();
        
        for (int i = 0; i < V; i++)
        {
            for (int j = 0; j < V; j++)
            {
                sb.Append($"{adj[i][j]}");
                if (j != (V - 1))
                    sb.Append('-');
            }
            sb.Append('\n');
        }

        return sb.ToString();
    }
}

class Program
{
    static void Main(string[] args)
    {
        Grafo g = new(4);
        g.AdicionarAresta(0, 1);
        g.AdicionarAresta(0, 2);
        g.AdicionarAresta(1, 2);
        g.AdicionarAresta(2, 3);

        if (g.Conexo())
            Console.WriteLine("O grafo é conexo.");
        else
            Console.WriteLine("O grafo não é conexo.");

        if (g.Completo())
            Console.WriteLine("O grafo é completo.");
        else
            Console.WriteLine("O grafo não é completo.");

        Console.WriteLine(g.ToString());
    }
}