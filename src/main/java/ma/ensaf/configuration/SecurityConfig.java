package ma.ensaf.configuration;

import ma.ensaf.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authenticationProvider(authenticationProvider())

            .authorizeHttpRequests(auth -> auth

                .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**").permitAll()

                .requestMatchers("/", "/index", "/login", "/produits", "/produits/details/**").permitAll()

                .requestMatchers("/api/produits/**").permitAll()

                .requestMatchers("/admin/**").hasRole("ADMIN")

                .requestMatchers("/produits/**").hasAnyRole("ADMIN", "PHARMACIEN", "CLIENT")
                .requestMatchers("/detail-produits/**").hasAnyRole("ADMIN", "PHARMACIEN", "CLIENT")

                .requestMatchers("/categories/**").hasAnyRole("ADMIN", "PHARMACIEN")
                .requestMatchers("/fournisseurs/**").hasAnyRole("ADMIN", "PHARMACIEN")
                .requestMatchers("/tags/**").hasAnyRole("ADMIN", "PHARMACIEN")

                .requestMatchers("/api/tag/**", "/api/fornisseur/**", "/api/Fornisseur/**").hasRole("ADMIN")
                .requestMatchers("/ventes/**").hasRole("CAISSIER")

                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/", true)  // Redirection après connexion
            )

            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            )

            .exceptionHandling(ex -> ex.accessDeniedPage("/access-denied"));

        return http.build();
    }
}
