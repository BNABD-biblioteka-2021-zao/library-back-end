package pl.weeia.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final String API = "/api/v1";
    private final LibraryUserDetailService libraryUserDetailService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfigurer(LibraryUserDetailService libraryUserDetailService, JwtRequestFilter jwtRequestFilter) {
        this.libraryUserDetailService = libraryUserDetailService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(libraryUserDetailService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers(API+"/auth/login", API+"/test/hello",API+"/auth/register")
                .permitAll()
                .antMatchers(API+"/auth/refresh-token",API+"/test/nice", API+"/user", API+"/book/all",
                        API+"/bookcopy/all", API+"/borrowing/allMy", API+"/borrowing/add", API+"/borrowing/**")
                .authenticated()
                .antMatchers(API+"/test/user")
                .hasRole("USER")
                .antMatchers(API+"/test/librarian", API+"/book/**", API+"/bookcopy/**",
                         API+"/borrowing/all",API+"/borrowing/edit",
                        API+"/user/all")
                .hasRole("LIBRARIAN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
