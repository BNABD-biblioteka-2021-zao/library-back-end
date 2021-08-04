package pl.weeia.library.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    private Long id;
    @NotNull
    private String title;
    private String genre;
    private String author;
    @Size(min = 0, max = 4096)
    private String description;

    @OneToMany(mappedBy = "book")
    private Set<BookCopy> copies;

    public Book(String title, String genre, String author, String description) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.description = description;
    }
}
