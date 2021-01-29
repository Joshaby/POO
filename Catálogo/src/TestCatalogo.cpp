# include "../include/Catalogo.h"
# include <iostream>

using namespace std;

void menu(Catalogo *catalogo) {
    while (1) {
        cout << "Catálogo: " + (*catalogo).getNome() << endl;
        cout << "=============================" << endl;
        cout << "   1. Pesquisar livro" << endl;
        cout << "   2. Pesquisar livros" << endl;
        cout << "   3. Adicionar livro" << endl;
        cout << "   4. Remover livro" << endl;
        cout << "   5. Informações" << endl;
        cout << "   6. Sair" << endl;
        cout << "   Digite um opção: ";
        int option = 0;
        int option1 = 0;
        string nome = "";
        string autor = "";
        int paginas = 0;
        int categoria = 0;
        cin >> option;
        if (option == 6) break;
        else {
            if (option == 1) {
                cout << "   Digite o nome do livro: ";
                cin >> nome;
                Livro *livro = new Livro();
                (*catalogo).getLivro(nome, livro);
                if (nome == (*livro).getNome()) {
                    cout << "Livro encontrado!" << endl;
                    cout << "\t" + (*livro).ToString();
                }
            }
            else if (option == 2) {
                cout << "   Opções de pesquisa:" << endl;
                cout << "       1. Autor" << endl;
                cout << "       2. Categoria" << endl;
                cout << "       3. Listar todos os livros" << endl;
                cout << "       Digite uma opção: ";
                cin >> option1;
                if (option1 == 1) {
                    cout << "       Digite o nome do autor: ";
                    cin >> autor;
                    vector<Livro> *livros = new vector<Livro>();
                    (*catalogo).getLivros(autor, livros);
                    if ((*livros).size() != 0) {
                        for (int i = 0; i < (*livros).size(); i ++) {
                            cout << (*livros).at(i).ToString() << endl;
                        }
                    }
                }
                else if (option1 == 2) {
                    int categoria = 0;
                    cout << "       Digite uma categoria: ";
                    cin >> categoria;
                    vector<Livro> *livros = new vector<Livro>();
                    (*catalogo).getLivros(categoria, livros);
                    if ((*livros).size() != 0) {
                        for (int i = 0; i < (*livros).size(); i ++) {
                            cout << (*livros).at(i).ToString() << endl;
                        }
                    }
                }
                else if (option1 == 3) {
                    vector<Livro> aux = (*catalogo).getLivros();
                    if (aux.size() == 0) {
                        cout << "       Catálogo não possuí livros!";
                    }
                    else {
                        for (int i = 0; i < aux.size(); i ++) {
                            cout << "       " + aux.at(i).ToString() << endl;
                        }
                    }
                }
            }
            else if (option == 3) {
                cout << "   Digite um nome de um livro: ";
                cin >> nome;
                cout << "   Digite o nome do autor: ";
                cin >> autor;
                cout << "   Digite o número de páginas do livro: ";
                cin >> paginas;
                cout << "   Catgeorias disponíveis:" << endl;
                cout << "       SEM_CATEGORIA(0)" << endl;
                cout << "       FISICA(1)" << endl;
                cout << "       GEOGRAFIA(2)" << endl;
                cout << "       PORTUGUES(3)" << endl;
                cout << "       CIENCIAS(4)" << endl;
                cout << "       INGLES(5)" << endl;
                cout << "       Digite uma opção: ";
                cin >> option1;
                if (option1 == 0) categoria = SEM_CATEGORIA;
                else if (option == 1) categoria = FISICA;
                else if (option == 2) categoria = GEOGRAFIA;
                else if (option == 3) categoria = PORTUGUES;
                else if (option == 4) categoria = CIENCIAS;
                else if (option == 5) categoria = INGLES;
                Livro livro(nome, autor, paginas, categoria);
                (*catalogo).addLivro(livro);
                cout << "   Livro adicionado! " << endl;
            }
            else if (option == 4) {
                cout << "   Digite um nome de um livro: ";
                cin >> nome;
                Livro *livro = new Livro();
                (*catalogo).removeLivro(nome, livro);
                if ((*livro).getNome() == nome) {
                    cout << "   Livro removido!" << endl;
                }
            }
            else if (option == 5) {
                cout << "   " + (*catalogo).toString();
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