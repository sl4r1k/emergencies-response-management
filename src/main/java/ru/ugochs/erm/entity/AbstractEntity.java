package ru.ugochs.erm.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Setter
@Getter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
}
