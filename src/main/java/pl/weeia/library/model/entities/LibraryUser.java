package pl.weeia.library.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;
    private String name;
    @NotBlank
    @JsonIgnore
    private String password;
    @Email
    @NotEmpty
    private String email;

    private String role;

    @JsonIgnore
    private String refreshToken;

//    @Fetch(FetchMode.JOIN)
//    @OneToMany(mappedBy = "bookCopy")
//    private Set<Borrowing> borrowings;

    public LibraryUser(String name, String password, String email, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public LibraryUser(Long userId) {
        this.id = userId;
    }
}
