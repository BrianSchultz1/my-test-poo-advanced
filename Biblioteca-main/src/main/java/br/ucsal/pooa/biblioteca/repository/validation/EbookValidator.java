package br.ucsal.pooa.biblioteca.repository.validation;

import br.ucsal.pooa.biblioteca.model.Book;

import java.lang.reflect.Field;

public class EbookValidator {
    public static boolean validateBook(Book book) {
        if (book == null) {
            return false;
        }
        Class<?> livroClass = book.getClass();
        Field[] fields = livroClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ISBN.class)) {
                ISBN isbnAnnotation = field.getAnnotation(ISBN.class);
                field.setAccessible(true);

                try {
                    String isbnValue = (String) field.get(book);
                    if (isbnValue == null || isbnValue.length() != 13) {
                        return false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
