#include "cliente.h"

cliente::cliente(){
    this->nome = "";
    this->sexo = ' ';
    this->idade = 0;
    this->cpf = -1;
    this->telefone = "";
}
cliente::cliente(string nome, char sexo, int idade, long long int cpf, string telefone){
    this->nome = nome;
    this->sexo = sexo;
    this->idade = idade;
    this->cpf = cpf;
    this->telefone = telefone;
}
void cliente::setNome(string nome){
    this->nome = nome;
}
void cliente::setSexo(char sexo){
    this->sexo = sexo;
}
void cliente::setIdade(int idade){
    this->idade = idade;
}
void cliente::setCpf(long long int cpf){
    this->cpf = cpf;
}
void cliente::setTelefone(string telefone){
    this->telefone = telefone;
}
string cliente::getNome(){
    return nome;
}
char cliente::getSexo(){
    return sexo;
}
int cliente::getIdade(){
    return idade;
}
long long int cliente::getCpf(){
    return cpf;
}
string cliente::getTelefone(){
    return telefone;
}
void cliente::preencheDados(){
    cout << "\nInforme os Seguintes Dados --->" << endl;
    cout << "Nome:  "; cin >> this->nome;
    cout << "Sexo:  "; cin >> this->sexo;
    cout << "Idade:  "; cin >> this->idade;
    cout << "CPF:  "; cin >> this->cpf;
    cout << "Telefone:  "; cin >> this->telefone;
}
void cliente::imprimeDados(){
    cout << "\nDados Cadastrados:\n" << endl;
    cout << "Nome: " << this->nome << endl;
    cout << "Sexo: " << this->sexo << endl;
    cout << "Idade: " << this->idade << endl;
    cout << "CPF: " << this->cpf << endl;
    cout << "Telefone: " << this->telefone << endl;
}
void cliente::editaCadastro(string novoNome, char novoSexo, int novaIdade, long long int novoCpf, string novoTelefone){
    this->nome = novoNome;
    this->sexo = novoSexo;
    this->idade = novaIdade;
    this->cpf = novoCpf;
    this->telefone = novoTelefone;
}
void cliente::copiar(cliente outro){
    this->nome = outro.getNome();
    this->sexo = outro.getSexo();
    this->idade = outro.getIdade();
    this->cpf = outro.getCpf();
    this->telefone = outro.getTelefone();
}