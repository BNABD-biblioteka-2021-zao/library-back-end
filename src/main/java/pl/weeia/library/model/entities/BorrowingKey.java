package pl.weeia.library.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BorrowingKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "book_copy_id")
    private  Long bookCopyId;

}
