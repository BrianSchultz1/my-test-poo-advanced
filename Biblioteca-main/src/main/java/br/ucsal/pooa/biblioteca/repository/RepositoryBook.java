package br.ucsal.pooa.biblioteca.repository;

import br.ucsal.pooa.biblioteca.model.Book;

public interface RepositoryBook {
    void toSave(Book livro);

    void toRemove(String isbn);

    Book toObtain(String isbn);
}
