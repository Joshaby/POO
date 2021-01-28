# include "../include/Catalogo.h"
# include <iostream>

using namespace std;

void menu(Catalogo *catalogo) {
    while (1) {
        cout << "Catálogo: " + (*catalogo).getNome() << endl;
        cout << "=============================" << endl;
        cout << "   1. Pesquisar livro" << endl;
        cout << "   2. Pesquisar livros" << endl;
        cout << "   3. Remover livro" << endl;
        cout << "   4. Informações" << endl;
        cout << "   5. Sair" << endl;
        cout << "   Digite um opção: ";
        int option = 0;
        cin >> option;
        if (option == 5) break;
        else {
            string nome = "";
            if (option == 1) {
                cout << "   Digite o nome do livro: ";
                cin >> nome;
                Livro *livro = new Livro();
                (*catalogo).getLivro(nome, livro);
                if (nome == (*livro).getNome()) {
                    cout << "\t" + (*livro).ToString();
                }
            }
            else if (option == 2) {
                cout << "   Digite o nome do autor: ";
                cin >> nome;
                vector<Livro> *livros = new vector<Livro>();
                (*catalogo).getLivros(nome, livros);
                if ((*livros).size() != 0) {
                    for (int i = 0; i < (*livros).size(); i ++) {
                        cout << (*livros).at(i).ToString() << endl;
                    }
                }
            }
        }
    }
}

int main() {
    Catalogo *catalogo = new Catalogo("Livros do zé");
    Livro livro("Rio", "Jose", 192, CIENCIAS);
    (*catalogo).addLivro(livro);
    menu(catalogo);
    delete catalogo;
    return 0;
}