#include "ArvoreBinaria.h"
#include<iostream>
#include<fstream>
#include<ostream>

using namespace std;

//construtores
ArvoreBinaria::ArvoreBinaria() {
    quant = 0;
    root = NULL;
}

ArvoreBinaria::ArvoreBinaria(const ArvoreBinaria &outro) {
    this->quant = outro.getQuant();
    this->root = outro.getRoot();
}

ArvoreBinaria::~ArvoreBinaria() {
    //será chamado quando o objeto for destruido
}

//métodos
No* ArvoreBinaria::buscar(No* subArvore, cliente &c) {
    if (subArvore != NULL) {
        cliente subCliente = subArvore->getCliente();
        if (c.getCpf() < subCliente.getCpf())
            return buscar(subArvore->getEsq(), c);
        else if (c.getCpf() > subCliente.getCpf())
            return buscar(subArvore->getDir(), c);
        else {
            return subArvore;
        }
    } else {
        cout << "\nElemento não encontrado!" << endl;
        return NULL;
    }
}

No* ArvoreBinaria::localEntrada(No *subArvore, cliente &c){
    cliente subCliente = subArvore->getCliente();
    if(c.getCpf() < subCliente.getCpf()){
        if(subArvore->getEsq() != NULL)
            return localEntrada(subArvore->getEsq(), c);
    }else{
        if(subArvore->getDir() != NULL)
            return localEntrada(subArvore->getDir(), c);
    }
    return subArvore;
}

void ArvoreBinaria::insert(cliente &c){
    No *novo = new No(c);

    if(root == NULL){
        root = new No();
        root->setCliente(c);
        quant ++;
    }else{
        No *pai = localEntrada(root, c);
        cliente cPai = pai->getCliente();

        if(c.getCpf() < cPai.getCpf()){
            pai->setEsq(novo);
            novo->setPai(pai);
            quant ++;
        }else if(c.getCpf() > cPai.getCpf()){
            pai->setDir(novo);
            novo->setPai(pai);
            quant ++;
        }else{
            cout << "\nElemento ja existe!" << endl;
        }
    }
}