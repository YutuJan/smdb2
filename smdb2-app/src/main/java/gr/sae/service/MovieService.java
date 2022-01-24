package gr.sae.service;

import gr.sae.domain.Genre;
import gr.sae.domain.Movie;
import gr.sae.domain.Occupation;
import gr.sae.transfer.MoviesAndSeriesPerGenreDto;
import gr.sae.transfer.MoviesPerGenrePerYearDto;
import gr.sae.transfer.TopRatedMovieDto;

import java.util.List;

public interface MovieService extends BaseService<Movie, Long> {
    Movie get(String title);

    Movie find(String title);

    void deleteByTitle(String title);

    List<Movie> findMoviesByRatingIsGreaterThanEqual(Double rating);

    TopRatedMovieDto findTopRatedMovie();

    List<Movie> findMoviesByGenresContaining(Genre genre);

    List<MoviesAndSeriesPerGenreDto> findMoviesPerGenre();

    List<MoviesPerGenrePerYearDto> findMoviesPerGenrePerYear();

    void addOccupation(Movie movie, Occupation occupation);

    void removeOccupation(Movie movie, Occupation occupation);

    void addPersonToMovieOccupation(Long personId, Long movieId, String roleType);

    void addPersonToMovieOccupation(String firstName, String lastName, String title, String roleType);

    void removePersonToMovieOccupation(Long personId, Long movieId, String roleType);

    void removePersonToMovieOccupation(String firstName, String lastName, String title, String roleType);
}
