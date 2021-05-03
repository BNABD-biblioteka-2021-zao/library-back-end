package pl.weeia.library.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@DynamicUpdate
@Data
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String title;
    private String genre;
    private String author;
    private String description;
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<BookCopy> copies;

    public Book(String title, String genre, String author, String description) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.description = description;
    }
}
