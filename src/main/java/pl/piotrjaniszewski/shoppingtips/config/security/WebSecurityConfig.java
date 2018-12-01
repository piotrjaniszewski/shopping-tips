package pl.piotrjaniszewski.shoppingtips.config.security;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final TokenAuthenticationService tokenAuthenticationService;

    public WebSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                             TokenAuthenticationService tokenAuthenticationService) {
        this.userDetailsService = userDetailsService;
        this.tokenAuthenticationService=tokenAuthenticationService;
    }

    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());//todo ważne nie skasować
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                // No need authentication.
                .antMatchers(HttpMethod.GET, "/public/**").permitAll()
                .antMatchers(HttpMethod.POST, "/public/**").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // Need user authentication.
                .antMatchers(HttpMethod.GET,"/user/**").hasAuthority("USER_PRIVILEGE")
                .antMatchers(HttpMethod.POST,"/user/**").hasAuthority("USER_PRIVILEGE")
                // Need moderator authentication.
                .antMatchers(HttpMethod.GET,"/mod/**").hasAuthority("MODERATOR_PRIVILEGE")
                .antMatchers(HttpMethod.POST,"/mod/**").hasAuthority("MODERATOR_PRIVILEGE")
                // Need admin authentication.
                .antMatchers(HttpMethod.GET,"/admin/**").hasAuthority("ADMIN_PRIVILEGE")
                .antMatchers(HttpMethod.POST,"/admin/**").hasAuthority("ADMIN_PRIVILEGE")
                // H2 database console
                .antMatchers("/console/**").permitAll()//h2 database console
                .antMatchers("/").permitAll()
                //
                .and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager(),tokenAuthenticationService, userDetailsService), UsernamePasswordAuthenticationFilter.class) // Add Filter 1 - JWTLoginFilter
                .addFilterBefore(new JWTAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)// Add Filter 2 - JWTAuthenticationFilter
                .logout();
        http.headers().frameOptions().disable();//h2 database console
    }
}
