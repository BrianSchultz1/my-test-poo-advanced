package br.ucsal.pooa.biblioteca.repository;

import br.ucsal.pooa.biblioteca.model.Book;
import br.ucsal.pooa.biblioteca.repository.RepositoryBook;

import java.sql.*;
import java.util.NoSuchElementException;

public class RepositoryBookDB implements RepositoryBook {
    private static final String URL = "jdbc:hsqldb:mem:bibliotecadb";
    private static final String USER = "SA";
    private static final String PASSWORD = "";

    private static Connection connection;

    static {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.createStatement().execute(
                    "CREATE TABLE Livro (" +
                            "isbn VARCHAR(13) NOT NULL PRIMARY KEY," +
                            "titulo VARCHAR(255) NOT NULL," +
                            "autor VARCHAR(255) NOT NULL" +
                            ")"
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void toSave(Book livro) {
        if (toObtain(livro.getIsbn()) != null) {
            throw new IllegalArgumentException("Já existe livro com esse isbn: " + livro.getIsbn());
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("INSERT INTO Livro (isbn, titulo, autor) VALUES (?, ?, ?)");
            pstmt.setString(1, livro.getIsbn());
            pstmt.setString(2, livro.getTitle());
            pstmt.setString(3, livro.getAuthor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Book toObtain(String isbn) {
        Book livro = null;
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Livro WHERE isbn = ?")) {
            pstmt.setString(1, isbn);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                livro = new Book(rs.getString("titulo"), rs.getString("autor"), rs.getString("isbn"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }

    public void toRemove(String isbn) {
        if (toObtain(isbn) == null) {
            throw new NoSuchElementException("Não foi encontrado livro com isbn: " + isbn);
        }
        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Livro WHERE isbn = ?")) {
            pstmt.setString(1, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
