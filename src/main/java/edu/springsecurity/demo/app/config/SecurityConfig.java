package edu.springsecurity.demo.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // указывается куда у пользователей есть доступ
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated() // если пользователь зашел на /authenticated, то пусать туда только аутентифицированных пользователей
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/"); // при логауте перенаправлять на /
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication().withUser(users.username("test").password("test").roles("ADMIN"));
//    }

//    @Bean
//    public UserDetailsService users() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2y$12$LFip8MtHVwgbmE2OqDI2TOM3edpD5HJlI5wZGLLICX1mP9.LCxo32")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$ET/i2mStEEzvmw51RMq4qufSGkfXo3pX17jKH0n2AxvH9QINRPe6O")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    JdbcUserDetailsManager users(DataSource dataSource) {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2y$12$LFip8MtHVwgbmE2OqDI2TOM3edpD5HJlI5wZGLLICX1mP9.LCxo32")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2y$12$ET/i2mStEEzvmw51RMq4qufSGkfXo3pX17jKH0n2AxvH9QINRPe6O")
                .roles("ADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        if(users.userExists(user.getUsername())) {
            users.deleteUser(user.getUsername());
        }
        if(users.userExists(admin.getUsername())) {
            users.deleteUser(admin.getUsername());
        }
        users.createUser(user);
        users.createUser(admin);
        return users;
    }
}
