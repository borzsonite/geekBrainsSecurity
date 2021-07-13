package edu.springsecurity.demo.app.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // указывается куда у пользователей есть доступ
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated() // если пользователь зашел на /authenticated, то пусать туда только аутентифицированных пользователей
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/"); // при логауте перенаправлять на /
    }

    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2y$12$3fZ2VKMD8l4KdGj0DuWJDeH.aOj9M/fxozHcAWETZeohrVge.XfoS ")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2y$12$3fZ2VKMD8l4KdGj0DuWJDeH.aOj9M/fxozHcAWETZeohrVge.XfoS ")
                .roles("ADMIN, USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
