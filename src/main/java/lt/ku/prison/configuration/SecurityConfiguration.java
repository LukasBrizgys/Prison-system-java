package lt.ku.prison.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lt.ku.prison.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
	
	@Autowired
	UserService userService;
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    public WebSecurityCustomizer webSecurityCustomizer(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		auth.userDetailsService(this.userService)
		.passwordEncoder(bc);
		return (web) -> {
			try {
				web.build();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
	}
    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests((authz) -> {
			try {
				authz
						.antMatchers("/").permitAll()
						.antMatchers("/prisoner/").permitAll()
						.antMatchers("/visitor/").permitAll()
						.antMatchers("/crime/").permitAll()
						.antMatchers("/prisoner/info/").hasAnyAuthority("Vyresnysis prižiūrėtojas,Prižiūrėtojas")
						.antMatchers("/prisoner/new/").hasAnyAuthority("Vyresnysis prižiūrėtojas")
						.antMatchers("/prisoner/update/*").hasAnyAuthority("Vyresnysis prižiūrėtojas")
						.antMatchers("/prisoner/delete/*").hasAnyAuthority("Vyresnysis prižiūrėtojas")
						.antMatchers("/prisoner/crimes/").hasAnyAuthority("Vyresnysis prižiūrėtojas,Prižiūrėtojas")
						.antMatchers("/prisoner/visitors/").hasAnyAuthority("Vyresnysis prižiūrėtojas,Prižiūrėtojas")
						.antMatchers("/prisoner/visitors/delete/").hasAnyAuthority("Vyresnysis prižiūrėtojas")
						.antMatchers("/prisoner/crimes/delete/").hasAnyAuthority("Vyresnysis prižiūrėtojas")
						.antMatchers("/css/*").permitAll()
						.antMatchers("/login").permitAll()
						.antMatchers("/logout").permitAll()
						.antMatchers("/register").permitAll()
						.anyRequest().authenticated()
						.and()
						.formLogin()
						.loginPage("/login")
						.usernameParameter("email").permitAll()
						.defaultSuccessUrl("/")
						.failureUrl("/login-error")
						.and()
						.logout()
						.logoutUrl("/logout")
						.logoutSuccessUrl("/");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				).httpBasic().disable();
		return http.build();
	}
	
	
}
