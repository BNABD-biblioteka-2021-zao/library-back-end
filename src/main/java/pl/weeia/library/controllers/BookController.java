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
    public Book insertBook(@RequestBody Book book) {
        return bookService.insertBook(book);
    }

    @PutMapping("/update")
    public Book updateBook(@RequestBody Book book) {
        return  bookService.updateBook(book);
    }
}
