package pl.weeia.library.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.weeia.library.model.entities.BookCopy;
import pl.weeia.library.repositories.BookCopyRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookCopyServiceImpl implements BookCopyService {

    private final BookCopyRepository copyRepository;

    @Override
    public List<BookCopy> findAll() {
        return copyRepository.findAll();
    }

    @Override
    public BookCopy insertBookCopy(BookCopy bookCopy) {
        return copyRepository.save(bookCopy);
    }

    @Override
    public BookCopy updateBook(BookCopy bookCopy) {
        if (copyRepository.existsById(bookCopy.getId())) {
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
