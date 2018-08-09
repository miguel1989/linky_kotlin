package linky.security

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfig(
        private val customUserDetailsAuthenticationProvider: CustomUserDetailsAuthenticationProvider,
        private val authManagerBuilder: AuthenticationManagerBuilder
) : WebSecurityConfigurerAdapter() {

    init {
        authManagerBuilder.authenticationProvider(customUserDetailsAuthenticationProvider)
    }

    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/api/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/",
                        "/*",
                        "/service/register",
                        "/logout")
                .permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/service/login").permitAll()
                .and().httpBasic()
//                .and().logout()
                .and().csrf().disable()
    }
}