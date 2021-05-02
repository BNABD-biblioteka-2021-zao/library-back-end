package pl.weeia.library.services;

import pl.weeia.library.model.entities.Book;

import java.util.List;

public interface BookService {
    
    List<Book> findAllBooks();
    Book insertBook(Book book);
    Book updateBook(Book book);
}
