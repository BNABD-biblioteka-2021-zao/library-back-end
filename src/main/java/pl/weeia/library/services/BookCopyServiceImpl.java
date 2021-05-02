package pl.weeia.library.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
}
