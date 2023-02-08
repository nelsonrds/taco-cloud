package nelson.tacocloud.configuration;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import nelson.tacocloud.model.User;
import nelson.tacocloud.repository.UserRepository;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            Optional<User> user = userRepository.readByUsername(username);
            if (user.isPresent())
                return user.get();
            throw new UsernameNotFoundException("User: " + username + " not found!");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf((csrf) -> csrf.disable())
                .headers((header) -> header.frameOptions().disable())

                .authorizeHttpRequests((requests) -> requests
                        // .requestMatchers(HttpMethod.GET,
                        // "/api/test").hasAuthority("SCOPE_viewOrders")
                        // .requestMatchers(HttpMethod.GET,
                        // "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                        .requestMatchers(HttpMethod.POST, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                        .requestMatchers(HttpMethod.DELETE, "/api/ingredients/**")
                        .hasAuthority("SCOPE_deleteIngredients")
                        .requestMatchers(HttpMethod.GET, "/data-api/tacoOrders").hasAuthority("SCOPE_viewOrders")
                        .requestMatchers(HttpMethod.GET, "/api/ingredients").hasAuthority("SCOPE_viewOrders")
                        .anyRequest().permitAll())

                // .oauth2Login((oauth2Login) ->
                // oauth2Login.loginPage("/oauth2/authorization/taco-admin-client"))
                // .oauth2Login((oauth2Login) ->
                // oauth2Login.loginPage("/oauth2/authorization/taco-admin-client"))
                // .oauth2Client(Customizer.withDefaults())
                // .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)

                // // .formLogin()
                // //
                // .loginPage("/login").loginProcessingUrl("/authenticate").usernameParameter("user")
                // // .passwordParameter("pass").defaultSuccessUrl("/design")
                // // // .and().oauth2Login()
                // // .and().logout().logoutSuccessUrl("/")
                // .and()

                .build();
    }

}
