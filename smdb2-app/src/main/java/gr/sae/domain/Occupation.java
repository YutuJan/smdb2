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
@Table(name = "OCCUPATIONS",
        uniqueConstraints = @UniqueConstraint(name = "UniqueVideoEntertainmentAndPerson",
                columnNames = {"VIDEO_ENTERTAINMENT_ID", "PERSON_ID"}))
@SequenceGenerator(name = "idGenerator", sequenceName = "OCCUPATIONS_SEQ", initialValue = 1, allocationSize = 1)
public class Occupation extends BaseModel {
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "OCCUPATION", nullable = false)
    private RoleType occupation;

    @JsonBackReference("videoEntertainment")
    @ManyToOne(optional = false)
    @JoinColumn(name = "VIDEO_ENTERTAINMENT_ID", referencedColumnName = "ID")
    private VideoEntertainment videoEntertainment;

    @JsonBackReference("person")
    @ManyToOne(optional = false)
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    private Person person;
}
