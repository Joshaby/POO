# include <iostream>
# include "CategoriaEnum.h"

using namespace std;

class Livro {
    private:
        string nome;
        string autor;
        int paginas;
        int categoria;
    public :
        Livro(string, string, int, int);
        Livro();
        string getNome();
        void setNome(string);
        string getAutor();
        void setAutor(string);
        int getPaginas();
        void setPaginas(int);
        int getCategoria();
        void setCategoria(int);
        string ToString();
};

Livro::Livro(string nome, string autor, int paginas, int categoria) {
    this -> nome = nome;
    this -> autor = autor;
    this -> paginas = paginas;
    this -> categoria = categoria;
}
Livro::Livro() {
    this -> nome = "Sem nome";
    this -> autor = "Sem autor";
    this -> paginas = 0;
    this -> categoria = SEM_CATEGORIA;
}

string Livro::getNome() {
    return nome;
}
void Livro::setNome(string nome) {
    this -> nome = nome;
}
string Livro::getAutor() {
    return autor;
}
void Livro::setAutor(string nome) {
    this -> autor = autor;
}
int Livro::getPaginas() {
    return paginas;
}
void Livro::setPaginas(int paginas) {
    this -> paginas = paginas;
}
int Livro::getCategoria() {
    return categoria;
}
void Livro::setCategoria(int categoria) {
    this -> categoria = categoria;
}
string Livro::ToString() {
    return "Livro\n======================\n\tNome: " + getNome() + "\n\tAutor: " + getAutor() + "\n\tQtde de páginas: " + to_string(getPaginas()) + "\n\tCategoria: " + to_string(getCategoria()) + "\n";
}