package pl.weeia.library.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCopy {
    @Id
    @GeneratedValue
    private Long id;
    private String ISBN;
    private LocalDate publishDate;
    private Long pageAmount;
    private String publisher;

    @OneToMany(mappedBy = "user")
    private Set<Borrowing> borrowings;
}
