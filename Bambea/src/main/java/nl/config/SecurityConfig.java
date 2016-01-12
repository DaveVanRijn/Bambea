package nl.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * The Security Config handles the login and authentication part of the application
 * @author Hugo van Rijswijk
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    DataSource dataSource;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * Configuration of who is allowed on what page
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //All login, static, error and api pages are available to everyone
        //Admin pages require the admin roles
        //Adding, editing and deleting beacons require admin or mod access
        //All other pages require either he admin or user role
        http.authorizeRequests()
                .antMatchers("/setup**").permitAll()
                .antMatchers("/login**").permitAll()
                .antMatchers("/static/js/beaconAddEdit.js").access("hasAnyRole('ROLE_ADMIN', 'ROLE_MOD')")
                .antMatchers("/static/js/partnerAccess.js").access("hasRole('ROLE_PARTNER')")
                .antMatchers("/static/**").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/registration/linkGen**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/beacon/add**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_MOD')")
                .antMatchers("/beacon/edit**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_MOD')")
                .antMatchers("/beacon/delete**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_MOD')")
                .antMatchers("/beacon/**").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_MOD', 'ROLE_PARTNER')")
            .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
                .loginProcessingUrl("/j_spring_security_check")
            .and()
                .logout().logoutSuccessUrl("/login?logout")
                .logoutUrl("/logout")
            .and()
                .csrf()
            .and()
                .rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(1209600).rememberMeCookieName("BambeaRememberMe");
    }
    
    @Bean PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
    
    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler(){
        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("targetUrl");
        return auth;
    }

    /**
     *
     * @return The BCrypt password encoder to be used with logins
     * Currently not used because of testing
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder;
        encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
