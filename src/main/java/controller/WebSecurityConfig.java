package controller;

import Beans.Person;
import DB.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/",
                        "/index",
                        "/home",
                        "/topVideos",
                        "/register",
                        "/loginUser",
                        "/css/**",
                        "/js/**",
                        "/Scripts/**",
                        "/Videos/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
                        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
                    }
                })
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.userDetailsService(inMemoryUserDetailsManager());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/getVideos", "/upload", "/uploadURL", "/registerUser", "/like", "/allTopVideos");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        PersonDAO personDAO = (PersonDAO) PersonDAO.context.getBean("personDAO");

        List<Person> personList = personDAO.listPerson();

        for (Person person : personList) {
            auth.inMemoryAuthentication().withUser(person.getEmail()).password("{noop}" + person.getPw()).roles("USER");
        }
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final Properties users = new Properties();
        users.put("user", "pass,USER,enabled"); //add whatever other user you need
        return new InMemoryUserDetailsManager(users);
    }

}
