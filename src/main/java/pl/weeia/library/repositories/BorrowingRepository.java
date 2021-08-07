package pl.weeia.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.weeia.library.model.entities.Borrowing;
import pl.weeia.library.model.entities.LibraryUser;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findAllByUser(LibraryUser user);
}
