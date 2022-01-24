package gr.sae.smdb2.repository;

import gr.sae.base.AbstractLogComponent;
import gr.sae.domain.Genre;
import gr.sae.domain.Movie;
import gr.sae.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MovieRepositoryTest extends AbstractLogComponent {
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

    @Test
    void findMovieByTitle() {
        createBunchOfMovies();

        // given
        String title = MOVIE_TITLE_002;

        // when
        Movie movie = movieRepository.findMovieByTitle(title);

        // then
        assertThat(movie).isNotNull();
        assertThat(title).isEqualTo(movie.getTitle());
    }

    @Test
    void getMovieByTitle() {
        createBunchOfMovies();

        // given
        String title = MOVIE_TITLE_002;

        // when
        Movie movie = movieRepository.getMovieByTitle(title);

        // then
        assertThat(movie).isNotNull();
        assertThat(title).isEqualTo(movie.getTitle());
    }

    private void createBunchOfMovies() {
        List<Movie> movies = List.of(
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