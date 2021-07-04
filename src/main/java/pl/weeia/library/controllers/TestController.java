package pl.weeia.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/nice")
    public String nice(){return "nice";}

    @GetMapping("/user")
    public String user() {
        return "hello user";
    }

    @GetMapping("/librarian")
    public String librarian() {
        return "hello librarian";
    }
}
