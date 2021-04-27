package pl.weeia.library.model.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String genre;
    private String author;
    private String description;


    public Book(String title, String genre, String author, String description) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.description = description;
    }
}
