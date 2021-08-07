package pl.weeia.library.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.weeia.library.model.enums.CopyStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "copy_seq")
    private Long id;

    private String ISBN;
    private LocalDate publishDate;
    private Long pageAmount;
    private String publisher;

    @Enumerated(EnumType.STRING)
    private CopyStatus status;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnore
    private Book book;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Borrowing> borrowings;

    public BookCopy( String ISBN, LocalDate publishDate, Long pageAmount, String publisher, Book book) {
        this.ISBN = ISBN;
        this.publishDate = publishDate;
        this.pageAmount = pageAmount;
        this.publisher = publisher;
        this.book = book;
    }

    public BookCopy(String ISBN, LocalDate publishDate, Long pageAmount, String publisher, Book book, CopyStatus status) {
        this.ISBN = ISBN;
        this.publishDate = publishDate;
        this.pageAmount = pageAmount;
        this.publisher = publisher;
        this.status = status;
        this.book = book;
    }

    public BookCopy(Long bookCopyId) {
        this.id = bookCopyId;
    }

    public BookCopy(long i) {
        this.id = i;
    }


}
