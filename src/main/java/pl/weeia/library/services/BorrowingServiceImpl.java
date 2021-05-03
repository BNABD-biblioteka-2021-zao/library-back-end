package pl.weeia.library.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.weeia.library.model.entities.Borrowing;
import pl.weeia.library.model.entities.BorrowingKey;
import pl.weeia.library.repositories.BorrowingRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BorrowingRepository borrowingRepository;

    @Override
    public List<Borrowing> findAllBorrowings() {
        return borrowingRepository.findAll();
    }

    @Override
    public Borrowing insertBorrowing(Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    @Override
    public Borrowing updateBorrowing(Borrowing borrowing) {
        if (borrowingRepository.existsById(borrowing.getId())) {
            return borrowingRepository.save(borrowing);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Borrowing with provided id doesn't exist");
        }
    }

    @Override
    public void deleteByIds(Long bookid, Long userid) {
        borrowingRepository.deleteById(new BorrowingKey(userid, bookid));
    }

    @Override
    public List<Borrowing> findAllMyBorrowings(String name) {
        return null;
    }
}
