package ru.ugochs.erm.configuration.security;

import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ugochs.erm.view.LoginView;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        super.configure(security);
        this.setLoginView(
            security,
            LoginView.class,
            Logout.URL
        );
        security.formLogin()
            .defaultSuccessUrl(
                "/emergencies",
                true
            );
    }

    @Override
    public void configure(WebSecurity security) throws Exception {
        super.configure(security);
        security.ignoring()
            .antMatchers(
                "/images/*.png"
            );
    }
}
