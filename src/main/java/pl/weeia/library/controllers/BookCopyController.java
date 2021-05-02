package pl.weeia.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.weeia.library.model.entities.BookCopy;
import pl.weeia.library.services.BookCopyService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/bookcopy")
public class BookCopyController {

    private final BookCopyService copyService;

    @GetMapping("/all")
    public List<BookCopy> getAll(){
        return copyService.findAll();
    }
    @PostMapping("/add")
    public BookCopy insertNewBookCopy(@RequestBody BookCopy bookCopy) {
        return copyService.insertBookCopy(bookCopy);
    }

}
