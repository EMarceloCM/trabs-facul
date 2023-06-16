#include <iostream>
#include <string>

using namespace std;

#pragma once

class cliente{

    private:
        string nome;
        char sexo;
        int idade;
        long long int cpf;
        string telefone;

    public:
        cliente();
        //cliente(cliente &outro);
        cliente(string nome, char sexo, int idade, long long int cpf, string telefone);
        //getters
        void setNome(string nome);
        void setSexo(char sexo);
        void setIdade(int idade);
        void setCpf(long long int cpf);
        void setTelefone(string telefone);
        //setters
        string getNome();
        char getSexo();
        int getIdade();
        long long int getCpf();
        string getTelefone();

        void preencheDados();
        void imprimeDados();
        void editaCadastro(string novoNome, char novoSexo, int novaIdade, long long int novoCpf, string novoTelefone);
        void copiar(cliente outro); //usado no insert e shiftEnd

};