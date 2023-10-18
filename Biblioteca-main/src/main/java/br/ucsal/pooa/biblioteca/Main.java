package br.ucsal.pooa.biblioteca;

import br.ucsal.pooa.biblioteca.repository.RepositoryBookDB;

public class Main {
    public static void main(String[] args) {
        RepositoryBookDB repositoryBook = new RepositoryBookDB();
        new Menu(repositoryBook).start();
    }
}
