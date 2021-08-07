package pl.weeia.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.weeia.library.model.entities.Borrowing;
import pl.weeia.library.services.BorrowingService;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/borrowing")
public class BorrowingController {

    private final BorrowingService borrowingService;

    @GetMapping("/allMy")
    public ResponseEntity<List<Borrowing>> findAllMyBorrowings(Principal principal) {
        System.out.println(principal.getName());
        return new ResponseEntity<>(borrowingService.findAllMyBorrowings(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Borrowing>> findAllBorrowings() {
        return new ResponseEntity<>(borrowingService.findAllBorrowings(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Borrowing> insertBorrowing(Principal principal, @RequestBody Borrowing borrowing) {
        System.out.println(borrowing);
        System.out.println(principal);
        System.out.println("***********************************************");
        return new ResponseEntity<>(borrowingService.insertBorrowing(borrowing), HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<Borrowing> updateBorrowing(@RequestBody Borrowing borrowing) {
        return new ResponseEntity<>(borrowingService.updateBorrowing(borrowing), HttpStatus.OK);
    }

    @DeleteMapping("/{borrowingId}")
    public ResponseEntity<?> deleteBorrowing(@PathVariable("borrowingId")Long borrowingId) {
        borrowingService.deleteById(borrowingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
