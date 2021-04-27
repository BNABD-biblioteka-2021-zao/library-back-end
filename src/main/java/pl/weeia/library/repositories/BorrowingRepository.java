package pl.weeia.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.weeia.library.model.entities.Borrowing;
import pl.weeia.library.model.entities.BorrowingKey;

public interface BorrowingRepository extends JpaRepository<Borrowing, BorrowingKey> {
}
