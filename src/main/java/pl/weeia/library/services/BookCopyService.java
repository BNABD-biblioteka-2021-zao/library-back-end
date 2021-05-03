package pl.weeia.library.services;

import pl.weeia.library.model.entities.BookCopy;

import java.util.List;

public interface BookCopyService {
    BookCopy insertBookCopy(BookCopy bookCopy);

    List<BookCopy> findAll();

    BookCopy updateBook(BookCopy bookCopy);

    void deleteById(Long id);
}
