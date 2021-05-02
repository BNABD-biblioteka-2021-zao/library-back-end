package pl.weeia.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.weeia.library.model.entities.Borrowing;
import pl.weeia.library.model.entities.BorrowingKey;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, BorrowingKey> {
}
