package pl.weeia.library.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.weeia.library.model.entities.LibraryUser;
import pl.weeia.library.repositories.LibraryUserRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class LibraryUserServiceImpl implements LibraryUserService{

    private final PasswordEncoder passwordEncoder;
    private final LibraryUserRepository userRepository;

    public LibraryUser getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void saveRefreshToken(String email, String refreshToken) {
        System.out.println("saving token");
        userRepository.updateUserByEmail(email, refreshToken);
        userRepository.flush();
    }

    public Long saveUser(LibraryUser user) {
        if (user.getPassword().length() < 8){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password to short");
        }
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ROLE_USER");
        LibraryUser libraryUser = userRepository.save(user);
        return libraryUser.getId();
    }

    @Override
    public String deleteMyAccount(String name) {
        if (userRepository.deleteByEmail(name) != 0){
            return "User deleted";
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User was not deleted");
        }
    }
}
