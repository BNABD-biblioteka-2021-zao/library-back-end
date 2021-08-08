package pl.weeia.library.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.weeia.library.model.DTOs.BookCopyModel;
import pl.weeia.library.model.DTOs.CopyUpdateModel;
import pl.weeia.library.model.entities.Book;
import pl.weeia.library.model.entities.BookCopy;
import pl.weeia.library.model.enums.CopyStatus;
import pl.weeia.library.repositories.BookCopyRepository;
import pl.weeia.library.repositories.BookRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookCopyServiceImpl implements BookCopyService {

    private final BookCopyRepository copyRepository;
    private final BookRepository bookRepository;

    @Override
    public List<BookCopy> findAll() {
        return copyRepository.findAll();
    }

    @Override
    public BookCopy insertBookCopy(BookCopyModel bookCopyModel) {
        Book book = bookRepository.findById(bookCopyModel.getBookId()).orElseThrow();
        BookCopy bookCopy = new BookCopy();
        bookCopy.setISBN(bookCopyModel.getISBN());
        bookCopy.setPageAmount(bookCopyModel.getPageAmount());
        bookCopy.setPublishDate(bookCopyModel.getPublishDate());
        bookCopy.setPublisher(bookCopyModel.getPublisher());
        bookCopy.setBook(book);
        bookCopy.setStatus(CopyStatus.available);
        return copyRepository.save(bookCopy);
    }

    @Override
    public BookCopy updateBook(CopyUpdateModel bookCopyModel) {
        if (copyRepository.existsById(bookCopyModel.getId())) {
//            Book book = bookRepository.findById(bookCopyModel.getBookId()).orElseThrow();
            BookCopy bookCopy = new BookCopy();
            bookCopy.setISBN(bookCopyModel.getISBN());
            bookCopy.setPageAmount(bookCopyModel.getPageAmount());
            bookCopy.setPublishDate(bookCopyModel.getPublishDate());
            bookCopy.setPublisher(bookCopyModel.getPublisher());
//            bookCopy.setBook(book);
            bookCopy.setStatus(CopyStatus.available);
            return copyRepository.save(bookCopy);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book copy with provided id doesn't exist");
        }
    }

    @Override
    public void deleteById(Long id) {
        copyRepository.deleteById(id);
    }
}
