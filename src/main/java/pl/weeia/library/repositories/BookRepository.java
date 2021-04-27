package pl.weeia.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.weeia.library.model.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
