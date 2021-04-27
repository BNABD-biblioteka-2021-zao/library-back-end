package pl.weeia.library.model.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @NotBlank
    private String password;
    @Email
    @NotEmpty
    private String email;
    private String role;
    @CreationTimestamp
    @Column(updatable = false, insertable = false)
    private LocalDateTime registration;
    private String refreshToken;

    @OneToMany(mappedBy = "bookCopy")
    private Set<Borrowing> borrowings;

    public LibraryUser(String name, String password, String email, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
