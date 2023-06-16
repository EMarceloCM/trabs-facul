#include "listaEncadeada.h"
#include "cliente.h"

listaEncadeada::listaEncadeada(){
    quant = 0;
    head = NULL;
    foot = NULL;
}

int listaEncadeada::getQuant(){
    return this->quant;
}

void listaEncadeada::insert(){
    cliente c;
    c.preencheDados();
    nodo *novo = new nodo(c);

    novo->setProx(head);
    novo->setAnt(foot);
    if(quant > 0){
        head->setAnt(novo);
        foot->setProx(novo);
    }
    head = novo;
    quant++;
}

void listaEncadeada::insert(int n){
    cliente c;
    c.preencheDados();
    nodo *novo = new nodo(c);

    nodo* posicao = NULL; //novo
    if(n <= quant){ //novo
        posicao = this->getElemento(n);
    }
    else if(n == (quant + 1)){
        posicao = head;
    }else{
        cout << "\nPosicao informada excede as " << n + 1 << " posicoes possiveis!" << endl;
    }
    nodo* anterior = posicao->getAnt();
    novo->setProx(posicao);
    novo->setAnt(anterior);
    anterior->setProx(novo);
    posicao->setAnt(novo);
    if(n == 1)//novo
        head == novo;
    if(n == (quant + 1))
        foot = novo;
    quant++;
}

void listaEncadeada::remove(){
    if(quant > 0){
        head = head->getProx();
        head->setAnt(foot); // novo
        foot->setProx(head); // novo
        quant--;
    }else{
        cout << "\nLista Vazia!!!" << endl;
    }
}

void listaEncadeada::remove(int n){
    nodo *anterior = this->getElemento(n-1);
    nodo *frente = anterior->getProx()->getProx();
    anterior->setProx(frente);
    frente->setAnt(anterior);
    if(n == 1)//novo
        head = frente;
    if(n == quant)
        foot = anterior;
    quant--;
}

nodo* listaEncadeada::getElemento(int n){
    nodo *p = head;
    int i = 1;

    if(n < (quant/2)){ //nova condição
        while(i < n && p->getProx() != nullptr){
            p = p->getProx();
            i++;
        }
    }else{
        i = quant;
        while(i > n && p->getAnt() != nullptr){
            p = p->getAnt();
            i--;
        }
    }
    
    if (i == n)
        return p;
    else
        return NULL;
}

void listaEncadeada::imprime(int n){
    nodo* obj = this->getElemento(n);
    obj->getCliente().imprimeDados();
}
