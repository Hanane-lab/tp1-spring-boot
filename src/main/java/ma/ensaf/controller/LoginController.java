package ma.ensaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // utilise src/main/resources/templates/login.html
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
