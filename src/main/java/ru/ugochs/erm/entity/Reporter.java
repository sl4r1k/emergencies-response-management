package ru.ugochs.erm.entity;

import lombok.*;
import javax.persistence.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "reporters")
@Entity
public class Reporter extends AbstractEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
