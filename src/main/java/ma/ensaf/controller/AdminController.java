package ma.ensaf.controller;

import ma.ensaf.entity.Role;
import ma.ensaf.entity.User;
import ma.ensaf.repository.RoleRepository;
import ma.ensaf.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public AdminController(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @GetMapping
    public String adminHome(Model model) {
        List<User> users = userRepo.findAll();
        List<Role> roles = roleRepo.findAll();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        return "admin/home"; // tu as admin/home.html
    }

    @PostMapping("/create")
    public String createUser(@RequestParam String username, @RequestParam String password, @RequestParam(required=false) List<Long> roleIds) {
        // pour simplicité on stockera le password encodé via InitData / service: ici tu peux
        // ajouter la logique ou créer via un endpoint REST qui encode.
        return "redirect:/admin";
    }
}
