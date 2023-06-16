#include "nodo.h"

nodo::nodo(){
    this->c = c;
    prox = nullptr;
    ant = nullptr;
}

nodo::nodo(cliente &c){
    this->c = c;
    prox = nullptr;
    ant = nullptr;
}

cliente nodo::getCliente(){
    return c;
}

void nodo::setCliente(cliente &c){
    this->c = c;
}

void nodo::setProx(nodo* prox){
    this->prox = prox;
}
nodo* nodo::getProx(){
    return this->prox;
}

void nodo::setAnt(nodo* ant){
    this->ant = ant;
}
nodo* nodo::getAnt(){
    return this->ant;
}