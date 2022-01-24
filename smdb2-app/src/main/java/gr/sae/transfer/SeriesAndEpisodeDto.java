package gr.sae.transfer;

import gr.sae.domain.Episode;
import gr.sae.domain.Series;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SeriesAndEpisodeDto {
    @NotNull
    Series series;

    @NotNull
    Episode episode;
}
