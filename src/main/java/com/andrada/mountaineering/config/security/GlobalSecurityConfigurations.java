package com.andrada.mountaineering.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
public class GlobalSecurityConfigurations extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;

    @Autowired
    public GlobalSecurityConfigurations(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.csrf().disable().cors().and()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/").permitAll()
                .mvcMatchers(HttpMethod.POST, "/login").permitAll()
                .mvcMatchers(HttpMethod.POST, "/events").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/events/*").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/events/*").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/events").hasAnyRole(new String[]{"ADMIN", "VIEWER"})
                .mvcMatchers(HttpMethod.POST, "/users/registration").permitAll()
                .anyRequest().authenticated().and().httpBasic().and().logout();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, active from person where username=?")
                .authoritiesByUsernameQuery("select u.username, r.role from person u join person_roles pr on u.id=pr.user_id join user_role r on r.id=pr.roles_id where u.username=?");
    }


}

