package ru.ugochs.erm.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Table(name = "emergencies")
@Entity
public class Emergency extends AbstractEntity {
    @Column(name = "happened", nullable = false)
    private LocalDateTime happened;

    @ManyToOne
    @JoinColumn(name = "index_id", nullable = false)
    private Index index;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "reporter_id", nullable = false)
    private Reporter reporter;

    @ManyToOne
    @JoinColumn(name = "street_id", nullable = false)
    private Street street;

    @Column(name = "house")
    private String house;

    @Column(name = "house_fraction")
    private String houseFraction;

    @Column(name = "floor")
    private String floor;

    @Column(name = "dead", nullable = false)
    private Integer dead;

    @Column(name = "injured", nullable = false)
    private Integer injured;

    @Column(name = "rescued", nullable = false)
    private Integer rescued;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "attracted_services",
        joinColumns = @JoinColumn(name = "emergency_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> services = new ArrayList<>();

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;

    @Column(name = "completed")
    private LocalDateTime completed;

    @Column(name = "result_description")
    private String resultDescription;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
