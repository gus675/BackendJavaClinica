package entregable.security;

import entregable.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConf extends WebSecurityConfigurerAdapter {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/turnosList.html").hasRole("USER")
                .antMatchers("/turnosAlta.html").hasRole("USER")
                .antMatchers("/getXfecha.html").hasRole("USER")

                .antMatchers("/pacientesList.html").hasRole("ADMIN")
                .antMatchers("/odontologosList.html").hasRole("ADMIN")
                .antMatchers("/pacientesAlta.html").hasRole("ADMIN")
                .antMatchers("/odontologosAlta.html").hasRole("ADMIN")
                .antMatchers("/getXmat.html").hasRole("ADMIN")
                .antMatchers("/getXmail.html").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .and().logout();
    }
}
