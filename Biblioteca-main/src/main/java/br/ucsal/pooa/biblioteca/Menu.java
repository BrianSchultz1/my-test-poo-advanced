package br.ucsal.pooa.biblioteca;

import br.ucsal.pooa.biblioteca.model.Book;
import br.ucsal.pooa.biblioteca.repository.RepositoryBook;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private RepositoryBook repositorioLivro;

    public Menu(RepositoryBook repositorioLivro) {
        this.repositorioLivro = repositorioLivro;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        do {
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Remover Livro");
            System.out.println("3. Consultar Livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
                if (opcao == 1) {
                    System.out.print("Digite o ISBN do livro: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    Book livro = new Book(titulo, autor, isbn);
                    repositorioLivro.toSave(livro);
                    System.out.println("Livro adicionado com sucesso!");
                } else if (opcao == 2) {
                    System.out.print("Digite o ISBN do livro a ser removido: ");
                    String isbn = scanner.nextLine();
                    repositorioLivro.toRemove(isbn);
                    System.out.println("Livro removido com sucesso!");
                } else if (opcao == 3) {
                    System.out.print("Digite o ISBN do livro a ser consultado: ");
                    String isbn = scanner.nextLine();
                    Book livro = repositorioLivro.toObtain(isbn);
                    if (livro != null) {
                        System.out.println("Livro encontrado: " + livro.getTitle() + ", " + livro.getAuthor());
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                } else if (opcao != 0) {
                    cleanScren();
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                }
            } catch (InputMismatchException e) {
                cleanScren();
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.next(); // limpa o buffer do scanner
            } catch (Exception e) {
                cleanScren();
                System.out.println(e.getMessage());
            }
        } while (opcao != 0);
        scanner.close();
    }

    public static void cleanScren() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
