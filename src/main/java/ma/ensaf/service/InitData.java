package ma.ensaf.service;


import ma.ensaf.entity.Role;
import ma.ensaf.entity.User;
import ma.ensaf.repository.RoleRepository;
import ma.ensaf.repository.UserRepository;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
public class InitData {

    @Bean
    CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, BCryptPasswordEncoder encoder) {
        return args -> {
            // create roles if not exist
            Role adminRole = roleRepo.findByName("ADMIN").orElseGet(() -> roleRepo.save(new Role("ADMIN")));
            Role pharmaRole = roleRepo.findByName("PHARMACIEN").orElseGet(() -> roleRepo.save(new Role("PHARMACIEN")));
            Role caisseRole = roleRepo.findByName("CAISSIER").orElseGet(() -> roleRepo.save(new Role("CAISSIER")));
            Role clientRole = roleRepo.findByName("CLIENT").orElseGet(() -> roleRepo.save(new Role("CLIENT")));

            // create users (if not existing)
            if (userRepo.findByUsername("admin").isEmpty()) {
                User u = new User();
                u.setUsername("admin");
                u.setPassword(encoder.encode("admin123"));
                u.setRoles(Set.of(adminRole));
                userRepo.save(u);
            }

            if (userRepo.findByUsername("pharma").isEmpty()) {
                User u = new User();
                u.setUsername("pharma");
                u.setPassword(encoder.encode("pharma123"));
                u.setRoles(Set.of(pharmaRole));
                userRepo.save(u);
            }

            if (userRepo.findByUsername("caisse").isEmpty()) {
                User u = new User();
                u.setUsername("caisse");
                u.setPassword(encoder.encode("caisse123"));
                u.setRoles(Set.of(caisseRole));
                userRepo.save(u);
            }

            if (userRepo.findByUsername("client").isEmpty()) {
                User u = new User();
                u.setUsername("client");
                u.setPassword(encoder.encode("client123"));
                u.setRoles(Set.of(clientRole));
                userRepo.save(u);
            }
        };
    }
}
