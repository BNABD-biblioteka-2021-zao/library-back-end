package pl.weeia.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.weeia.library.services.LibraryUserServiceImpl;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final LibraryUserServiceImpl userService;

}
