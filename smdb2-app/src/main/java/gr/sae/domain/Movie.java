package gr.sae.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Entity
@Table(name = "MOVIES")
@SequenceGenerator(name = "idGenerator", sequenceName = "MOVIES_SEQ", initialValue = 1, allocationSize = 1)
public class Movie extends VideoEntertainment {
    @EqualsAndHashCode.Exclude
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRES", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Set<Genre> genres = new HashSet<>();
}
