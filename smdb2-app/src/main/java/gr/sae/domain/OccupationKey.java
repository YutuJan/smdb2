package gr.sae.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class OccupationKey implements Serializable {
    @Column(name = "VIDEO_ENTERTAINMENT_ID")
    private Long videoEntertainmentId;

    @Column(name = "PERSON_ID")
    private Long personId;
}
