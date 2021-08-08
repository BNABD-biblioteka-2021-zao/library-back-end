package pl.weeia.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.weeia.library.model.DTOs.BookCopyModel;
import pl.weeia.library.model.DTOs.CopyUpdateModel;
import pl.weeia.library.model.entities.BookCopy;
import pl.weeia.library.services.BookCopyService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/bookcopy")
public class BookCopyController {

    private final BookCopyService copyService;

    @GetMapping("/all")
    public List<BookCopyModel> getAll(){
        return copyService.findAll();
    }

    @PostMapping
    public BookCopy insertNewBookCopy(@RequestBody BookCopyModel bookCopy) {
        return copyService.insertBookCopy(bookCopy);
    }

    @PutMapping
    public ResponseEntity<BookCopy> updateBook(@RequestBody CopyUpdateModel bookCopy) {
        return new ResponseEntity<>(copyService.updateBook(bookCopy), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCopy(@PathVariable("id")Long id) {
        copyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
