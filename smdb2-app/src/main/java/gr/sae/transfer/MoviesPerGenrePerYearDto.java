package gr.sae.transfer;

import gr.sae.domain.Genre;

public interface MoviesPerGenrePerYearDto {
    Long getYear();

    Genre getGenre();

    Long getCount();
}
