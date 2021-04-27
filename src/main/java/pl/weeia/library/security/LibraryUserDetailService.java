package pl.weeia.library.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.weeia.library.model.entities.LibraryUser;
import pl.weeia.library.repositories.LibraryUserRepository;

import java.util.Arrays;
import java.util.Collections;

@Service
@AllArgsConstructor
public class LibraryUserDetailService implements UserDetailsService {

    private final LibraryUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LibraryUser user = userRepository.findByEmail(email);
        System.out.println("detail serv user " +user.toString());
        User authUser = new User(user.getEmail(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
        System.out.println(authUser);
        return authUser;
    }

}
