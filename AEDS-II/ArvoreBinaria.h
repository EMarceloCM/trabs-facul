#pragma once
#include "No.h"

class ArvoreBinaria
{
    private:
        int quant;
        No *root;

        void removeZeroF(No *sair);
        void removeUmF(No *sair);
        void removeNF(No *sair);

        No* localEntrada(No *subArvore, cliente &c);
        No* menorDescendente(No *no);
        No* maiorDescendente(No *no);
    public:
        //construtores
        ArvoreBinaria(/* args */);
        ArvoreBinaria(const ArvoreBinaria &outro);
        virtual ~ArvoreBinaria();

        //métodos
        void insert(cliente &c);
        void remove(cliente &c);
        No* buscar(No *subArvore, cliente &outro);

        No* getSucessor(No *no);
        No* getAntecessor(No *no);

        void emOrdem(No *no);
        void posOrdem(No *no);
        void preOrdem(No *no);
        void preOrdemArquivo(No *no);

        void loadProdutos();
        void saveProdutosArquivo();
        
        //getters e setters
        void setQuant(int quant);
        int getQuant() const;
        void setRoot(No* root);
        No* getRoot() const;
};