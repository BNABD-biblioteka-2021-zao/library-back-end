package pl.weeia.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.weeia.library.model.entities.Book;
import pl.weeia.library.services.BookService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/all")
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping("/add")
    public Book insertNewBook(@RequestBody Book book) {
        return bookService.insertNewBook(book);
    }
}
