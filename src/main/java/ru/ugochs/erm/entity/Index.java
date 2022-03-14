package ru.ugochs.erm.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name = "indexes")
@Entity
public class Index extends AbstractEntity {
    @ManyToOne
    private Index parent;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="parent")
    private List<Index> children;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "level", nullable = false)
    private Integer level;

    public Index(String name) {
        this.parent = null;
        this.name = name;
        this.level = 1;
    }

    public Index(@NonNull Index parent, String name) {
        if (parent.getLevel().equals(3)) {
            throw new IllegalArgumentException(
                "Иерархия индексов не должна превышать 3 уровня"
            );
        }
        this.parent = parent;
        this.name = name;
        this.level = parent.getLevel() + 1;
    }
}
