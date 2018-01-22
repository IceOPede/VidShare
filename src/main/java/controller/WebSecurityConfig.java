package controller;

import Beans.Person;
import DB.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/",
                        "/index",
                        "/home",
                        "/topVideos",
                        "/register",
                        "/getVideos",
                        "/registerUser",
                        "/loginUser",
                        "/like",
                        "/allTopVideos",
                        "/css/**",
                        "/js/**",
                        "/Scripts/**",
                        "/Videos/**",
                        "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        PersonDAO personDAO = (PersonDAO) PersonDAO.context.getBean("personDAO");

        List<Person> personList = personDAO.listPerson();

        for (Person person: personList){
            auth.inMemoryAuthentication().withUser(person.getEmail()).password("{noop}"+person.getPw()).roles("USER");
        }
    }
}
