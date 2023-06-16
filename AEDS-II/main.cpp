#include <iostream>
#include <stdio.h>
#include "cliente.h"
#include "listaEncadeada.h"

using namespace std;

int main(){

    listaEncadeada lista;

    lista.insert();
    lista.insert(1);
    lista.imprime(1);
    lista.imprime(2);

    return 0;
}