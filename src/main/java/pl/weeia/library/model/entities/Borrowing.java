package pl.weeia.library.model.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import pl.weeia.library.model.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class Borrowing {

//    @EmbeddedId
//    private BorrowingKey id;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrowing_seq")
    private Long id;

    @ManyToOne
//    @MapsId("userId")
    @JoinColumn(name = "user_id")
//    @JsonIgnore
    private LibraryUser user;

    @ManyToOne
//    @MapsId("bookCopyId")
    @JoinColumn(name = "book_copy_id")
//    @JsonIgnore
    private BookCopy bookCopy;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Nullable
    private LocalDateTime reservationTime;
    @Nullable
    private LocalDateTime borrowStartTime;
    @Nullable
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
