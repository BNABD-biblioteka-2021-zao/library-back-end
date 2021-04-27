package pl.weeia.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.weeia.library.model.entities.Book;
import pl.weeia.library.model.entities.LibraryUser;
import pl.weeia.library.repositories.BookRepository;
import pl.weeia.library.repositories.BorrowingRepository;
import pl.weeia.library.repositories.LibraryUserRepository;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class SampleDataProvider implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final LibraryUserRepository userRepo;
    private final BookRepository bookRepo;
    private final BorrowingRepository borrowingRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepo.saveAll(Arrays.asList(
                new LibraryUser("test", passwordEncoder.encode("pass"), "email@email.email", "USER"),
                new LibraryUser("test2", passwordEncoder.encode("pass"), "email2@email.email", "LIBRARIAN")
        ));
        ObjectMapper mapper = new ObjectMapper();

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
