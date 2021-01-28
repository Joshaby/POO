# include <iostream>

using namespace std;

class LivroException {
    private:
        string message;
    public:
        LivroException(string);
        string getMessage();
};

LivroException::LivroException(string message) {
    this -> message = message;
}

string LivroException::getMessage() {
    return message;
}