package pl.weeia.library.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.weeia.library.model.entities.Book;
import pl.weeia.library.repositories.BookRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book insertNewBook(Book book) {
        return bookRepository.save(book);
    }
}
