package pl.weeia.library;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.weeia.library.model.entities.Book;
import pl.weeia.library.model.entities.BookCopy;
import pl.weeia.library.model.entities.LibraryUser;
import pl.weeia.library.repositories.BookCopyRepository;
import pl.weeia.library.repositories.BookRepository;
import pl.weeia.library.repositories.BorrowingRepository;
import pl.weeia.library.repositories.LibraryUserRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class SampleDataProvider implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final LibraryUserRepository userRepo;
    private final BookRepository bookRepo;
    private final BookCopyRepository copyRepository;
    private final BorrowingRepository borrowingRepo;

    @Override
    public void run(ApplicationArguments args) {
        List<LibraryUser> users = userRepo.saveAll(Arrays.asList(
                new LibraryUser("test", passwordEncoder.encode("password"), "email@email.email", "ROLE_USER"),
                new LibraryUser("librarian", passwordEncoder.encode("password"), "librarian@email.lib", "ROLE_LIBRARIAN")
        ));
        List<Book> books = bookRepo.saveAll(Arrays.asList(
           new Book("Pan Tadeusz", "Epopeja", "Adam Mickiewicz", "Some desc"),
           new Book("Zemsta", "Komedia", "Aleksander Fredro", "wać Panie")
        ));
        List<BookCopy> copies = copyRepository.saveAll(Arrays.asList(
           new BookCopy("7894161", LocalDate.now(), 255L, "KEN", books.get(0)),
           new BookCopy("9111611", LocalDate.now().minusDays(20), 125L, "KEN", books.get(1))
        ));

//        List<Borrowing> borrowings = borrowingRepo.saveAll(Arrays.asList(
//           new Borrowing(LocalDateTime.now(), LocalDateTime.now().plusDays(15))
//        ));





//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(new LibraryUser("test2", passwordEncoder.encode("pass"), "email2@email.email", "LIBRARIAN")));
//        bookRepo.saveAll(Arrays.asList(
//                new Book("Pan Tadeusz", "Romance", "Mickiewicz Adam", "Borring AF", LocalDate.now(), 247L),
//                new Book("Quo Vadis", "Historical", "Sienkiewicz Henryk", "Rome stuff", LocalDate.now(), 380L)
//        ));

//        System.out.println(userRepo.findById(1L).get().toString());
//        System.out.println(bookRepo.findById(1L).get().toString());
//        borrowingRepo.saveAll(Arrays.asList(
//                new Borrowing(userRepo.findById(1L).get(), bookRepo.findById(1L).get(), OffsetDateTime.now(), OffsetDateTime.now().plusDays(10))
//        ));
    }
}
