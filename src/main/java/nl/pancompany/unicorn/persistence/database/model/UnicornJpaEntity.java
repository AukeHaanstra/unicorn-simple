package nl.pancompany.unicorn.persistence.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.AccessType.FIELD;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;


@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "unicorn")
//@Access(FIELD)
public class UnicornJpaEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "unicorn", cascade = ALL, fetch = LAZY, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<UnicornLegJpaEntity> legs = new HashSet<>();

    public void addLeg(UnicornLegJpaEntity leg) {
        if (legs == null) {
            legs = new HashSet<>();
        }
        legs.add(leg);
        leg.setUnicorn(this);
    }

}
