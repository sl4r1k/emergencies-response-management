package ru.ugochs.erm.entity;

import lombok.*;
import javax.persistence.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Table(name = "districts")
@Entity
public class District extends AbstractEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public District(String name) {
        this.name = name;
    }
}
