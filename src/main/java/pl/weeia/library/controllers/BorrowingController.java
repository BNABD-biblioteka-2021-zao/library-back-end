package pl.weeia.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.weeia.library.model.entities.Borrowing;
import pl.weeia.library.services.BorrowingService;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/borrowing")
public class BorrowingController {

    private final BorrowingService borrowingService;

    @GetMapping("/allMy")
    public ResponseEntity<List<Borrowing>> findAllMyBorrowings(Principal principal) {
        return new ResponseEntity<>(borrowingService.findAllMyBorrowings(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Borrowing>> findAllBorrowings() {
        return new ResponseEntity<>(borrowingService.findAllBorrowings(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Borrowing> insertBorrowing(@RequestBody Borrowing borrowing) {
        return new ResponseEntity<>(borrowingService.insertBorrowing(borrowing), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Borrowing> updateBorrowing(@RequestBody Borrowing borrowing) {
        return new ResponseEntity<>(borrowingService.updateBorrowing(borrowing), HttpStatus.OK);
    }

    @DeleteMapping("/{bookid}/{userid}")
    public ResponseEntity<?> deleteBorrowing(@PathVariable("bookid")Long bookid, @PathVariable("userid")Long userid) {
        borrowingService.deleteByIds(bookid,userid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
