package ru.ugochs.erm.entity;

import lombok.*;
import org.hibernate.annotations.Formula;
import javax.persistence.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Table(name = "employees")
@Entity
public class Employee extends AbstractEntity {
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Setter(AccessLevel.NONE)
    @Formula("CONCAT(last_name, ' ', first_name, ' ', middle_name)")
    private String fullName;

    @Setter(AccessLevel.NONE)
    @Formula("CONCAT(last_name, ' ', SUBSTRING(first_name, 0, 2), '. ', SUBSTRING(middle_name, 0, 2), '.')")
    private String initials;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;
}
