package gr.sae.service;

import gr.sae.domain.Episode;
import gr.sae.domain.Genre;
import gr.sae.domain.Series;
import gr.sae.transfer.MoviesAndSeriesPerGenreDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeriesService extends BaseService<Series, Long> {
    Series get(String title);

    Series find(String title);

    void deleteByTitle(String title);

    List<Series> findByGenre(Genre genre);

    @Query(nativeQuery = true)
    List<MoviesAndSeriesPerGenreDto> findSeriesPerGenre();

    void addEpisode(Series series, Episode episode);

    void updateEpisode(Series series, Episode episode);

    void removeEpisode(Series series, Episode episode);
}
