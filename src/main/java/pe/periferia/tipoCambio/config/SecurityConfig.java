package pe.periferia.tipoCambio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/tipo-cambio/convertir").authenticated() // Solo usuarios autenticados pueden acceder a este endpoint
                .antMatchers("/api/tipo-cambio/tipo-cambio").authenticated() // Solo usuarios autenticados pueden acceder a este endpoint
                .anyRequest().permitAll() // Permitir acceso a todos los demás endpoints sin autenticación
                .and()
                .httpBasic(); // Usar autenticación básica (puedes configurar otros métodos de autenticación)
    }

   /* @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }*/
}

