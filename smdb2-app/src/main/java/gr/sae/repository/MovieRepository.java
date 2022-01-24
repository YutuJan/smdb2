package gr.sae.repository;

import gr.sae.domain.Genre;
import gr.sae.domain.Movie;
import gr.sae.transfer.MoviesAndSeriesPerGenreDto;
import gr.sae.transfer.MoviesPerGenrePerYearDto;
import gr.sae.transfer.TopRatedMovieDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieByTitle(String title);

    Movie getMovieByTitle(String title);

    List<Movie> findMoviesByGenresContaining(Genre genre);

    List<Movie> findMoviesByRatingIsGreaterThanEqual(Double rating);

    @Query(nativeQuery = true)
    List<MoviesAndSeriesPerGenreDto> findMoviesPerGenre();

    @Query(nativeQuery = true)
    List<MoviesPerGenrePerYearDto> findMoviesPerGenrePerYear();

    @Query(nativeQuery = true)
    TopRatedMovieDto findTopRatedMovie();
}
