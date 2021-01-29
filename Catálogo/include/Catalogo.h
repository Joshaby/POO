# include "Livro.h"
# include "LivroException.h"
# include <iostream>
# include <vector>

using namespace std;

class Catalogo {
    private:
        string nome;
        vector<Livro> *livros;
    public:
        Catalogo(string);
        Catalogo();
        string getNome();
        void setNome(string);
        vector<Livro> getLivros();
        void setLivros(vector<Livro>);
        void getLivros(string, vector<Livro> *livros);
        void getLivros(int, vector<Livro> *livros);
        void getLivro(string, Livro *Livro);
        void addLivro(Livro);
        void removeLivro(string, Livro *livro);
        string showAllLivros();
        string toString();
};

Catalogo::Catalogo(string nome) {
    this -> nome = nome;
    livros = new vector<Livro>();
}
Catalogo::Catalogo() {
    this -> nome = "Sem nome";
    livros = new vector<Livro>();
}

string Catalogo::getNome() {
    return nome;
}
void Catalogo::setNome(string nome) {
    this -> nome = nome;
}
vector<Livro> Catalogo::getLivros() {
    return *livros;
}
void Catalogo::getLivros(string autor, vector<Livro> *livros) {
    for (int i = 0; i < (*this -> livros).size(); i ++) {
        if((*this -> livros).at(i).getAutor() == autor) {
            (*livros).push_back((*this -> livros).at(i));
        }
    }
}
void Catalogo::getLivros(int categoria, vector<Livro> *livros) {
    for (int i = 0; i < (*this -> livros).size(); i ++) {
        if((*this -> livros).at(i).getCategoria() == categoria) {
            (*livros).push_back((*this -> livros).at(i));
        }
    }
}
void Catalogo::getLivro(string nome, Livro *livro) {
    try {
        for (int i = 0; i < (*livros).size(); i ++) {
            if ((*livros).at(i).getNome() == nome) {
                *livro = (*livros).at(i);
                return;
            }
        }
        throw LivroException("Livro não encontrado!");
    }
    catch (LivroException e) {
        cout << e.getMessage() << endl;
    }
}
void Catalogo::addLivro(Livro livro) {
    (*livros).push_back(livro);
}
void Catalogo::removeLivro(string nome, Livro *livro) {
    for (int i = 0; (*livros).size(); i ++) {
        if ((*livros).at(i).getNome() == nome) {
            *livro = (*livros).at(i);
            (*livros).erase((*livros).begin() + i);
        }
    }
}
string Catalogo::showAllLivros() {
    string output = toString();
    for (int i = 0; (*livros).size(); i ++) {
        output += (*livros).at(i).ToString();
    }
    return output;
}
string Catalogo::toString() {
    return "Catálogo\n======================\nNome: " + getNome() + "\n";
}