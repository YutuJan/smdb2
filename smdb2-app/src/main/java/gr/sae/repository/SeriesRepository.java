package gr.sae.repository;

import gr.sae.domain.Genre;
import gr.sae.domain.Series;
import gr.sae.transfer.MoviesAndSeriesPerGenreDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    Series findByTitle(String title);

    Series getByTitle(String title);

    List<Series> findSeriesByGenresContaining(Genre genre);

    @Query(nativeQuery = true)
    List<MoviesAndSeriesPerGenreDto> findSeriesPerGenre();

}
