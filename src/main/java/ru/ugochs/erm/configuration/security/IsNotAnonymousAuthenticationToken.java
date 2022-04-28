package ru.ugochs.erm.configuration.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import java.util.function.Predicate;

public class IsNotAnonymousAuthenticationToken implements Predicate<Authentication> {
    @Override
    public boolean test(Authentication authentication) {
        return !(authentication instanceof AnonymousAuthenticationToken);
    }
}
