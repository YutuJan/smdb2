package gr.sae.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "OCCUPATIONS")
public class Occupation {
    @EmbeddedId
    private OccupationKey key;

    @JsonBackReference("videoEntertainment")
    @ManyToOne(optional = false)
    @MapsId("videoEntertainmentId")
    @JoinColumn(name = "VIDEO_ENTERTAINMENT_ID")
    private VideoEntertainment videoEntertainment;

    @JsonBackReference("person")
    @ManyToOne(optional = false)
    @MapsId("personId")
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "OCCUPATION", nullable = false)
    private RoleType occupation;
}
