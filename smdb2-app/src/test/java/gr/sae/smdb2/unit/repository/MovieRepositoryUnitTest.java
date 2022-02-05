package gr.sae.smdb2.unit.repository;

import gr.sae.base.AbstractLogComponent;
import gr.sae.domain.Genre;
import gr.sae.domain.Movie;
import gr.sae.repository.MovieRepository;
import gr.sae.transfer.MoviesAndSeriesPerGenreDto;
import gr.sae.transfer.MoviesPerGenrePerYearDto;
import gr.sae.transfer.TopRatedMovieDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MovieRepositoryUnitTest extends AbstractLogComponent {
    @Autowired
    private MovieRepository movieRepository;

    private final String SUMMARY_000 = "Neo (Keanu Reeves) believes that Morpheus (Laurence Fishburne), an elusive" +
            " figure considered to be the most dangerous man alive, can answer his question -- What is the Matrix?" +
            " Neo is contacted by Trinity (Carrie-Anne Moss), a beautiful stranger who leads him into an underworld" +
            " where he meets Morpheus. They fight a brutal battle for their lives against a cadre of viciously" +
            " intelligent secret agents. It is a truth that could cost Neo something more precious than his life.";
    private final String SUMMARY_001 = "Traveling to inspect an abandoned mine his father owns in Crete, English" +
            " author Basil (Alan Bates) meets the exuberant peasant Zorba (Anthony Quinn) and invites him along when" +
            " the older man claims he has mining experience. In Basil's father's old village, he finds himself" +
            " attracted to a young widow (Irene Papas), and Zorba takes up with the woman who runs their hotel" +
            " (Lila Kedrova). When things go wrong, Zorba teaches Basil how to enjoy life even under the most" +
            " trying circumstances.";
    private final String SUMMARY_002 = "When a Chinese diplomat's daughter is kidnapped in Los Angeles, he calls in" +
            " Hong Kong Detective Inspector Lee (Jackie Chan) to assist the FBI with the case. But the FBI doesn't" +
            " want anything to do with Lee, and they dump him off on the LAPD, who assign wisecracking Detective" +
            " James Carter (Chris Tucker) to watch over him. Although Lee and Carter can't stand each other, they" +
            " choose to work together to solve the case on their own when they figure out they've been ditched by" +
            " both the FBI and police.";

    private final String MOVIE_TITLE_000 = "The Matrix";
    private final String MOVIE_TITLE_001 = "Zorba the Greek";
    private final String MOVIE_TITLE_002 = "Rush Hour";

    List<Movie> movies;

    @BeforeEach
    void setUp() {
        createBunchOfMovies();
    }

    @Test
    void ensureFindMovieByTitle() {
        // given
        String title = MOVIE_TITLE_002;

        // when
        Movie movie = movieRepository.findMovieByTitle(title);

        // then
        assertAll("Ensure that the repository finds the the right movie.",
                () -> assertNotNull(movie, "Repository must not return a null value."),
                () -> assertEquals(title, movie.getTitle(), "Movie's title must be " + title + "."));
    }

    @Test
    void ensureGetMovieByTitle() {
        // given
        String title = MOVIE_TITLE_002;

        // when
        Movie movie = movieRepository.getMovieByTitle(title);

        // then
        assertAll("Ensure that the repository gets the the right movie.",
                () -> assertNotNull(movie, "Repository must not return a null value."),
                () -> assertEquals(title, movie.getTitle(), "Movie's title must be " + title + "."));
    }

    @Test
    void ensureFindMoviesByGenresContaining() {
        // given
        Movie actionMovie0 = movies.get(0);
        Movie actionMovie1 = movies.get(2);
        Movie nonActionMovie = movies.get(1);

        // when
        List<Movie> actionMovies = movieRepository.findMoviesByGenresContaining(Genre.ACTION);

        // then
        assertAll("Ensure that the repository returns all action movies and no other movies.",
                () -> assertTrue(checkIfAListOfMoviesContainAMovie(actionMovies, actionMovie0),
                        "Movie with title " + actionMovie0.getTitle() + " must be in action movies."),
                () -> assertTrue(checkIfAListOfMoviesContainAMovie(actionMovies, actionMovie1),
                        "Movie with title " + actionMovie1.getTitle() + " must be in action movies."),
                () -> assertFalse(checkIfAListOfMoviesContainAMovie(actionMovies, nonActionMovie),
                        "Movie with title " + nonActionMovie.getTitle() + " must not be in action movies."),
                () -> assertEquals(2, actionMovies.size(),
                        "Total number of action movies must be 2."));
    }

    @Test
    void ensureFindMoviesByRatingIsGreaterThanEqual() {
        // given
        Movie movieWithRatingGreaterThan7_0 = movies.get(0);
        Movie movieWithRatingGreaterThan7_1 = movies.get(1);
        Movie movieWithRatingNotGreaterThan7 = movies.get(2);

        // when
        List<Movie> moviesWithRatingGreaterThan7 = movieRepository.findMoviesByRatingIsGreaterThanEqual(7.1);

        // then
        assertAll("Ensure that the repository returns all movies with rating greater or equal than 7.1 " +
                        "and no other movies.",
                () -> assertTrue(checkIfAListOfMoviesContainAMovie(
                        moviesWithRatingGreaterThan7, movieWithRatingGreaterThan7_0),
                        "Movie with title " + movieWithRatingGreaterThan7_0.getTitle() +
                                " must be movies with rating greater or equal than 7.1."),
                () -> assertTrue(checkIfAListOfMoviesContainAMovie(
                        moviesWithRatingGreaterThan7, movieWithRatingGreaterThan7_1),
                        "Movie with title " + movieWithRatingGreaterThan7_1.getTitle() +
                                " must be movies with rating greater or equal than 7.1"),
                () -> assertFalse(checkIfAListOfMoviesContainAMovie(
                        moviesWithRatingGreaterThan7, movieWithRatingNotGreaterThan7),
                        "Movie with title " + movieWithRatingNotGreaterThan7.getTitle() +
                                " must be movies with rating greater or equal than 7.1"),
                () -> assertEquals(2, moviesWithRatingGreaterThan7.size(),
                        "Total number of  movies with rating greater or equal than 7.1 must be 2."));
    }

    private boolean checkIfAListOfMoviesContainAMovie(List<Movie> movies, Movie movie) {
        for (Movie m: movies) {
            if (m.getTitle().equals(movie.getTitle())) {
                return true;
            }
        }
        return false;
    }

    @DisplayName("Grouped tests for checking MovieRepository.findMoviesPerGenre() method.")
    @Nested
    class EnsureFindMoviesPerGenre {
        @ParameterizedTest(name = "moviesAndSeriesPerGenre.get({0}).getGenre() == {1}")
        @CsvSource({"0, ACTION", "1, COMEDY", "2, DRAMA", "3, SCI_FI", "4, SPORTS"})
        void checkGenreFromFindMoviesPerGenre(int position, String genre) {
            // when
            List<MoviesAndSeriesPerGenreDto> moviesAndSeriesPerGenre = movieRepository.findMoviesPerGenre();

            // then
            assertEquals(moviesAndSeriesPerGenre.get(position).getGenre().toString(), genre);
        }

        @ParameterizedTest(name = "moviesAndSeriesPerGenre.get({0}).getCount() == {2}")
        @CsvSource({"0, 2", "1, 1", "2, 1", "3, 1", "4, 1"})
        void checkCountFromFindMoviesPerGenre(int position, int count) {
            // when
            List<MoviesAndSeriesPerGenreDto> moviesAndSeriesPerGenre = movieRepository.findMoviesPerGenre();

            // then
            assertEquals(moviesAndSeriesPerGenre.get(position).getCount(), count);
        }
    }

    @DisplayName("Grouped tests for checking MovieRepository.findMoviesPerGenrePerYear() method.")
    @Nested
    class EnsureFindMoviesPerGenrePerYear {
        @ParameterizedTest(name = "moviesPerGenrePerYear.get({0}).getGenre() == {2}")
        @CsvSource({"0, ACTION", "1, ACTION", "2, COMEDY", "3, DRAMA", "4, SCI_FI", "5, SPORTS"})
        void ensureGenreFromFindMoviesPerGenrePerYear(int position, String genre) {
            // when
            List<MoviesPerGenrePerYearDto> moviesPerGenrePerYear = movieRepository.findMoviesPerGenrePerYear();

            // then
            assertEquals(moviesPerGenrePerYear.get(position).getGenre().toString(), genre);
        }

        @ParameterizedTest(name = "moviesPerGenrePerYear.get({0}).getCount() == {2}")
        @CsvSource({"0, 1", "1, 1", "2, 1", "3, 1", "4, 1", "5, 1"})
        void ensureCountFromFindMoviesPerGenrePerYear(int position, int count) {
            // when
            List<MoviesPerGenrePerYearDto> moviesPerGenrePerYear = movieRepository.findMoviesPerGenrePerYear();

            // then
            assertEquals(moviesPerGenrePerYear.get(position).getCount(), count);
        }

        @ParameterizedTest(name = "moviesPerGenrePerYear.get({0}).getYear() == {2}")
        @CsvSource({"0, 1999", "1, 2007", "2, 2007", "3, 1964", "4, 1999", "5, 2007"})
        void ensureYearFromFindMoviesPerGenrePerYear(int position, int year) {
            // when
            List<MoviesPerGenrePerYearDto> moviesPerGenrePerYear = movieRepository.findMoviesPerGenrePerYear();

            // then
            assertEquals(moviesPerGenrePerYear.get(position).getYear(), year);
        }
    }

    @Test
    void ensureFindTopRatedMovie() {
        // given
        Movie actualTopRatedMovie = movies.get(0);

        // when
        TopRatedMovieDto topRatedMovie = movieRepository.findTopRatedMovie();

        // then
        assertAll("Ensure that the repository returns the top rated movie.",
                () -> assertEquals(actualTopRatedMovie.getTitle(), topRatedMovie.getTitle(),
                        "Top rated movie must have title " + topRatedMovie.getTitle()),
                () -> assertEquals(actualTopRatedMovie.getSummary(), topRatedMovie.getSummary(),
                        "Top rated movie must have summary" + topRatedMovie.getSummary()),
                () -> assertEquals(actualTopRatedMovie.getRating(), topRatedMovie.getRating(),
                        "Top rated movie must have rating" + topRatedMovie.getRating()));
    }

    private void createBunchOfMovies() {
        movies = List.of(
                Movie.builder()
                        .title(MOVIE_TITLE_000)
                        .summary(SUMMARY_000)
                        .durationInSeconds(8160)
                        .releaseDate(LocalDate.of(1999, 3, 31))
                        .distributor("Warner Bros. Pictures")
                        .rating(8.7)
                        .genres(Set.of(Genre.ACTION, Genre.SCI_FI))
                        .build(),
                Movie.builder()
                        .title(MOVIE_TITLE_001)
                        .summary(SUMMARY_001)
                        .durationInSeconds(8520)
                        .releaseDate(LocalDate.of(1964, 12, 14))
                        .distributor("Michael Cacoyannis") //TODO may not be accurate
                        .rating(7.7)
                        .genres(Set.of(Genre.DRAMA))
                        .build(),
                Movie.builder()
                        .title(MOVIE_TITLE_002)
                        .summary(SUMMARY_002)
                        .durationInSeconds(5880)
                        .releaseDate(LocalDate.of(2007, 5, 22))
                        .distributor("New Line Cinema")
                        .rating(7.0)
                        .genres(Set.of(Genre.ACTION, Genre.COMEDY, Genre.SPORTS))
                        .build()
        );

        logger.info("Created {} products.", movieRepository.saveAll(movies).size());
    }
}