package org.tp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecuriteConfig extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordEncoder;
	 @Autowired
	 public  SecuriteConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	 }
	 
	@Override
	//passer toutes les requêtes HTTP à travers la chaîne de filtres de sécurité
    protected void configure(HttpSecurity http) throws Exception {
        http
        //définir les roles
                
                .authorizeRequests().antMatchers("/Accueil").permitAll()
                .antMatchers("/Admin").hasRole("admin")                
                .antMatchers("/User").hasRole("user")
                .anyRequest()
                .authenticated()
                //Démarrez par le formulaire Spring Security par défaut
                .and().formLogin()
                .loginPage("/login").permitAll()
                .and().httpBasic()
                .disable();
    }
	@Bean
	public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails admin = User.builder()
				.username("admin")
				//.password("{noop}password")
				.password(passwordEncoder.encode("admin"))
				.roles("ADMIN")
				.build();
		
		UserDetails user = User.builder()
				.username("user")
				//.password("{noop}password")
				.password(passwordEncoder.encode("amin"))
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(admin, user);
	}
}
