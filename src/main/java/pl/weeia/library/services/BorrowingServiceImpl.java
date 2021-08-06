package pl.weeia.library.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.weeia.library.model.entities.BookCopy;
import pl.weeia.library.model.entities.Borrowing;
import pl.weeia.library.model.entities.LibraryUser;
import pl.weeia.library.model.enums.CopyStatus;
import pl.weeia.library.model.enums.Status;
import pl.weeia.library.repositories.BookCopyRepository;
import pl.weeia.library.repositories.BorrowingRepository;
import pl.weeia.library.repositories.LibraryUserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BookCopyRepository copyRepository;
    private final LibraryUserRepository userRepository;

    @Override
    public List<Borrowing> findAllBorrowings() {
        return borrowingRepository.findAll();
    }

    @Override
    public Borrowing insertBorrowing(Borrowing borrowing) {
        System.out.println(borrowing);
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getAuthorities());
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
        BookCopy copy = copyRepository.findById(borrowing.getBookCopy().getId()).orElseThrow();
        LibraryUser user = userRepository.findById(borrowing.getUser().getId()).orElseThrow();
        System.out.println(user);
        System.out.println(copy);
        borrowing.setUser(user);
        copy.setStatus(CopyStatus.unavailable);
        borrowing.setBookCopy(copy);
        if (hasUserRole) {
            borrowing.setStatus(Status.reservation);
            borrowing.setReservationTime(LocalDate.now());
            borrowing.setBorrowStartTime(null);
            borrowing.setBorrowEndTime(null);
        }else {
            borrowing.setStatus(Status.borrowed);
            if (borrowing.getBorrowStartTime() == null){
                borrowing.setBorrowStartTime(LocalDate.now());
            }
        }
        return borrowingRepository.save(borrowing);
    }

    @Override
    public Borrowing updateBorrowing(Borrowing borrowing) {
        if (borrowingRepository.existsById(borrowing.getId())) {
            if (borrowing.getStatus().equals(Status.returned)){
                BookCopy bc = copyRepository.findById(borrowing.getBookCopy().getId()).orElseThrow();
                bc.setStatus(CopyStatus.available);
                copyRepository.save(bc);
                borrowing.setBookCopy(bc);
            }
            System.out.println(borrowing.getBookCopy().getStatus());
            System.out.println(borrowing.getBookCopy());
            return borrowingRepository.save(borrowing);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Borrowing with provided id doesn't exist");
        }
    }

    @Override
    public void deleteById(Long borrowingId) {
//        borrowingRepository.deleteById(new BorrowingKey(userid, bookid));
        borrowingRepository.deleteById(borrowingId);
    }

    @Override
    public List<Borrowing> findAllMyBorrowings(String name) {
        return null;
    }
}
