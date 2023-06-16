#include "nodo.h"
#include <iostream>

#pragma once
//originalmente era encadeada
class listaEncadeada
{
    private:
        int quant;
        nodo* head;
        nodo* foot;
    public:
        listaEncadeada();
        void insert();
        void insert(int n);
        void remove();
        void remove(int n);
        nodo* getElemento(int n);

        void imprime(int n);

        int getQuant();
};
