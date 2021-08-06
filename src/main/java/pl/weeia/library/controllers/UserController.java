package pl.weeia.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.weeia.library.model.entities.LibraryUser;
import pl.weeia.library.services.LibraryUserServiceImpl;

import java.security.Principal;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final LibraryUserServiceImpl userService;

    @GetMapping
    public ResponseEntity<LibraryUser> getMyData(Principal principal) {
        return new ResponseEntity<LibraryUser>(userService.findByEmail(principal.getName()), HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteMyAccount(Principal principal) {
//        return principal.getName();
        return new ResponseEntity<>(userService.deleteMyAccount(principal.getName()), HttpStatus.ACCEPTED);
    }

}
