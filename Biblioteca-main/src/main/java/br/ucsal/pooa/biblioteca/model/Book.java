package br.ucsal.pooa.biblioteca.model;

import br.ucsal.pooa.biblioteca.repository.validation.ISBN;

public class Book {
    String title;
    String author;
    @ISBN
    String isbn;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

}