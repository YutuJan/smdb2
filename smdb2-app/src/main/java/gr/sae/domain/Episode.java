package gr.sae.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "EPISODES")
@SequenceGenerator(name = "idGenerator", sequenceName = "EPISODES_SEQ", initialValue = 1, allocationSize = 1)
public class Episode extends VideoEntertainment {
    @NotNull(message = "Season's number cannot be null")
    @Column(name = "SEASON", nullable = false)
    private Integer season;

    @JsonBackReference("episodes")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "SERIES_ID", referencedColumnName = "ID")
    private Series series;

    //For some unknown reason removeOccupation() from its parent class is not working for this one.
    @Override
    public void removeOccupation(Occupation occupation) {
        Set<Occupation> setOfOccupations = new HashSet<>();

        for (Occupation o : occupations) {
            if (o.equals(occupation)) {
                continue;
            }
            setOfOccupations.add(o);
        }

        occupations = setOfOccupations;
    }
}
