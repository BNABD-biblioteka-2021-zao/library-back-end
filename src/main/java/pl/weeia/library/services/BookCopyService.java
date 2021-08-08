package pl.weeia.library.services;

import pl.weeia.library.model.DTOs.BookCopyModel;
import pl.weeia.library.model.DTOs.CopyUpdateModel;
import pl.weeia.library.model.entities.BookCopy;

import java.util.List;

public interface BookCopyService {
    BookCopy insertBookCopy(BookCopyModel bookCopy);

    List<BookCopyModel> findAll();

    BookCopy updateBook(CopyUpdateModel bookCopy);

    void deleteById(Long id);
}
