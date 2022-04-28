package ru.ugochs.erm.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import ru.ugochs.erm.entity.Employee;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetEmployeeByLogin;
import java.util.Collections;

@Service
public class EmployeeDetails implements UserDetailsService {
    private final Db db;

    @Autowired
    public EmployeeDetails(Db db) {
        this.db = db;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = new GetEmployeeByLogin(
            username,
            this.db
        ).perform()
            .orElseThrow(
                () -> new UsernameNotFoundException(
                    "Нет пользователя с таким логином: " + username
                )
            );
        return new User(
            employee.getLogin(),
            employee.getPassword(),
            Collections.singletonList(
                new SimpleGrantedAuthority(
                    employee.getRole().systemName()
                )
            )
        );
    }
}
