package pl.weeia.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.weeia.library.model.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
