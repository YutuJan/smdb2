package gr.sae.transfer;

import gr.sae.domain.Genre;

public interface MoviesAndSeriesPerGenreDto {
    Genre getGenre();

    Long getCount();
}
