#include "cliente.h"
#include <iostream>

#pragma once

class nodo
{
    private:
        cliente c;
        nodo *prox;
        nodo *ant;
    public:
        nodo();
        nodo(cliente &c);

        cliente getCliente();
        void setCliente(cliente &c);
        void setProx(nodo* prox);
        nodo* getProx();
        void setAnt(nodo* ant);
        nodo* getAnt();
};