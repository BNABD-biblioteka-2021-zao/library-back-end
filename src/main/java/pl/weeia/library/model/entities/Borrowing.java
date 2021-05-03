package pl.weeia.library.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class Borrowing {

    @EmbeddedId
    private BorrowingKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private LibraryUser user;

    @ManyToOne
    @MapsId("bookCopyId")
    @JoinColumn(name = "book_copy_id")
    @JsonIgnore
    private BookCopy bookCopy;

    private LocalDateTime borrowStartTime;
    private LocalDateTime borrowEndTime;

    public Borrowing(LibraryUser user, BookCopy bookCopy, LocalDateTime borrowStartTime, LocalDateTime borrowEndTime) {
        this.user = user;
        this.bookCopy = bookCopy;
        this.borrowStartTime = borrowStartTime;
        this.borrowEndTime = borrowEndTime;
    }

    public Borrowing(LocalDateTime borrowStartTime, LocalDateTime borrowEndTime) {
        this.borrowStartTime = borrowStartTime;
        this.borrowEndTime = borrowEndTime;
    }
}
