package br.ucsal.pooa.biblioteca.repository;

import br.ucsal.pooa.biblioteca.model.Book;
import br.ucsal.pooa.biblioteca.repository.RepositoryBook;

import java.util.HashMap;
import java.util.Map;

public class RepositoryBookMemory implements RepositoryBook {
    private Map<String, Book> books;

    public RepositoryBookMemory() {
        this.books = new HashMap<>();
    }

    @Override
    public void toSave(Book book) {
        books.put(book.getIsbn(), book);
    }

    @Override
    public void toRemove(String isbn) {
        books.remove(isbn);
    }

    @Override
    public Book toObtain(String isbn) {
        return books.get(isbn);
    }
}
