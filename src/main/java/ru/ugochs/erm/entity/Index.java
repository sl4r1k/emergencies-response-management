package ru.ugochs.erm.entity;

import lombok.*;
import ru.ugochs.erm.exception.IllegalArgumentException;
import javax.persistence.*;
import java.util.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "indexes")
@Entity
public class Index extends AbstractEntity {
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Index parent;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Index> children = new ArrayList<>();

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Setter(AccessLevel.NONE)
    @Column(name = "level", nullable = false)
    private Integer level;

    public Index() {
        this.level = 1;
    }

    public void setParent(Index parent) {
        if (Objects.nonNull(parent)) {
            if (parent.getLevel().equals(3)) {
                throw new IllegalArgumentException(
                    "Иерархия индексов не должна превышать 3 уровня"
                );
            }
            this.parent = parent;
            this.level = this.parent.getLevel() + 1;
        }
    }
}
