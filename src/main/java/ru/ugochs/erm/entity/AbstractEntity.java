package ru.ugochs.erm.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

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
