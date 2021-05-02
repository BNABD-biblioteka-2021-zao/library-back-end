package pl.weeia.library.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @OneToMany(mappedBy = "user")
    private Set<Borrowing> borrowings;

    public BookCopy( String ISBN, LocalDate publishDate, Long pageAmount, String publisher, Book book) {
        this.ISBN = ISBN;
        this.publishDate = publishDate;
        this.pageAmount = pageAmount;
        this.publisher = publisher;
        this.book = book;
    }

}
