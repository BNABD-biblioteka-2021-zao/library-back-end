package pl.weeia.library.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.weeia.library.model.DTOs.UserInsertModel;
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

    @Override
    public LibraryUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void saveRefreshToken(String email, String refreshToken) {
        System.out.println("saving token");
        userRepository.updateUserByEmail(email, refreshToken);
        userRepository.flush();
    }

    public Long saveUser(UserInsertModel user) {
        if (user.getPassword().length() < 8){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password to short");
        }
        LibraryUser libraryUser = new LibraryUser();
        String password = user.getPassword();
        libraryUser.setPassword(passwordEncoder.encode(password));
        libraryUser.setEmail(user.getEmail());
        libraryUser.setName(user.getName());
        libraryUser.setRole("ROLE_USER");
        libraryUser = userRepository.save(libraryUser);
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
