package pl.weeia.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.weeia.library.model.entities.BookCopy;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
}
