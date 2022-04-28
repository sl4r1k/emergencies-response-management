package ru.ugochs.erm.configuration.security;

import lombok.experimental.Delegate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

public class CurrentAuthentication implements Authentication {
    @Delegate
    private final Authentication authentication;

    public CurrentAuthentication() {
        this.authentication = Optional.ofNullable(
            SecurityContextHolder.getContext().getAuthentication()
        ).filter(
            new IsNotAnonymousAuthenticationToken()
        ).orElse(null);
    }
}
