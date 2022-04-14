package ru.ugochs.erm.entity;

import lombok.*;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "password", nullable = false)
    private String password;

    public String fullName() {
        return String.format(
            "%s %s %s",
            this.lastName,
            this.firstName,
            this.middleName
        );
    }

    public String initials() {
        return String.format(
            "%s %s. %s.",
            this.lastName,
            this.firstName.substring(0, 1),
            this.lastName.substring(0, 1)
        );
    }
}
