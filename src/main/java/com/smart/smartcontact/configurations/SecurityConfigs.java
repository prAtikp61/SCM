package com.smart.smartcontact.configurations;
import com.smart.smartcontact.Services.servicesImpl.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfigs {

    @Autowired
    private SecurityUserService takeit;

    @Autowired
    private Oauthsuccesshandler oauthsuccesshandler; // Inject your OAuth success handler here

    @Bean
    public DaoAuthenticationProvider functionname() {
        DaoAuthenticationProvider exdao = new DaoAuthenticationProvider();
        exdao.setUserDetailsService(takeit);
        exdao.setPasswordEncoder(passwordEncoder());
        return exdao;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register", "/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        // form login configuration
        http.formLogin(formLogin -> {
            formLogin.loginPage("/signin");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.defaultSuccessUrl("/user/dashboard", true);
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
        });

        // Disable CSRF (if needed for your use case)
        http.csrf(AbstractHttpConfigurer::disable);

        // Logout configuration
        http.logout(logoutf -> {
            logoutf.logoutUrl("/do-logout");
            logoutf.logoutSuccessUrl("/signin?logout=true");
        });

        // OAuth2 login configuration with custom success handler
        http.oauth2Login(oauth -> {
            oauth.loginPage("/signin");
            oauth.successHandler(oauthsuccesshandler); // Add the custom success handler
        });

        return http.build();
    }
}
