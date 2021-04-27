package pl.weeia.library.services;

import pl.weeia.library.model.entities.Book;

import java.util.List;

public interface BookService {
    
    List<Book> findAllBooks();

    Book insertNewBook(Book book);
}
