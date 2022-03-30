package ru.ugochs.erm.entity;

import lombok.*;
import javax.persistence.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "streets")
@Entity
public class Street extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private District district;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
