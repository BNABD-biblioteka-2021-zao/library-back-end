package pl.weeia.library.services;

import pl.weeia.library.model.entities.Borrowing;

import java.util.List;

public interface BorrowingService {
    List<Borrowing> findAllBorrowings();

    Borrowing insertBorrowing(Borrowing borrowing);

    Borrowing updateBorrowing(Borrowing borrowing);

    void deleteByIds(Long bookid, Long userid);

    List<Borrowing> findAllMyBorrowings(String name);
}
