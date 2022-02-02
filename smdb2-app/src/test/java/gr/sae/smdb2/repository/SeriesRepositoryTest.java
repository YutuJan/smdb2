package gr.sae.smdb2.repository;

import gr.sae.base.AbstractLogComponent;
import gr.sae.domain.Episode;
import gr.sae.domain.Genre;
import gr.sae.domain.Series;
import gr.sae.domain.SeriesCategory;
import gr.sae.repository.EpisodeRepository;
import gr.sae.repository.SeriesRepository;
import gr.sae.transfer.MoviesAndSeriesPerGenreDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SeriesRepositoryTest extends AbstractLogComponent {
    @Autowired
    private EpisodeRepository episodeRepository;
    @Autowired
    private SeriesRepository seriesRepository;

    private final String SUMMARY_000 = "A notorious hacker takes an interest in cyber security engineer and vigilante" +
            " styled computer hacker Elliot, while an evil corporation is hacked.";
    private final String SUMMARY_001 = "Elliot is hesitant about fsociety; Elliot is offered a new job; Elliot" +
            " worries about Shayla's association with Fernando Vera; Ollie is given a CD outside of Allsafe; Mr." +
            " Robot makes Elliot talk about his father.";
    private final String SUMMARY_002 = "Monica and the gang introduce Rachel to the \"real world\" after she leaves" +
            " her fiancé at the altar.";
    private final String SUMMARY_003 = "Ross finds out his ex-wife is pregnant. Rachel returns her engagement ring to" +
            " Barry. Monica becomes stressed when her and Ross's parents come to visit.";

    private final String SERIES_INFO_000 = "Young, anti-social computer programmer Elliot works as a cybersecurity" +
            " engineer during the day, but at night he is a vigilante hacker. He is recruited by the mysterious" +
            " leader of an underground group of hackers to join their organization. Elliot's task? Help bring down" +
            " corporate America, including the company he is paid to protect, which presents him with a moral" +
            " dilemma. Although he works for a corporation, his personal beliefs make it hard to resist the urge" +
            " to take down the heads of multinational companies that he believes are running -- and ruining -- the" +
            " world.";
    private final String SERIES_INFO_001 = "Three young men and three young women -- of the BFF kind -- live in the" +
            " same apartment complex and face life and love in New York. They're not above sticking their noses" +
            "into one another's business and swapping romantic partners, which always leads to the kind of hilarity" +
            " average people will never experience -- especially during breakups.";

    private final String SERIES_TITLE_001 = "Mr. Robot";
    private final String SERIES_TITLE_002 = "Friends";

    private final String EPISODE_TITLE_001 = "eps1.0_hellofriend.mov";
    private final String EPISODE_TITLE_002 = "eps1.1_ones-and-zer0es.mpeg";
    private final String EPISODE_TITLE_003 = "The One Where Monica Gets a Roommate";
    private final String EPISODE_TITLE_004 = "The One with the Sonogram at the End";

    @BeforeEach
    void setUp() {
        createBunchOfSeriesAndEpisodes();
    }

    @Test
    void ensureFindSeriesByTitle() {
        // given
        String title = SERIES_TITLE_002;

        // when
        Series series = seriesRepository.findByTitle(title);

        // then
        assertAll("Ensure that the repository finds the right series.",
                () -> assertNotNull(series, "Repository must not return a null value."),
                () -> assertEquals(title, series.getTitle(), "Series' title must be " + title + "."));
    }

    @Test
    void ensureGetSeriesByTitle() {
        // given
        String title = SERIES_TITLE_002;

        // when
        Series series = seriesRepository.getByTitle(title);

        // then
        assertAll("Ensure that the repository gets the right series.",
                () -> assertNotNull(series, "Repository must not return a null value."),
                () -> assertEquals(title, series.getTitle(), "Series' title must be " + title + "."));
    }

    @Test
    void ensureFindSeriesByGenresContaining() {
        // given
        String title = SERIES_TITLE_002;

        // when
        List<Series> series = seriesRepository.findSeriesByGenresContaining(Genre.COMEDY);

        // then
        assertAll("Ensure that the repository returns the right series.",
                () -> assertNotNull(series, "Repository must not return a null value."),
                () -> assertEquals(title, series.get(0).getTitle(), "Series' title must be " + title + "."),
                () -> assertEquals(1, series.size(), "Repository must return a list of size 1."));
    }

    @DisplayName("Grouped tests for checking SeriesRepository.findSeriesPerGenre() method.")
    @Nested
    class EnsureFindSeriesByGenresContaining {
        @ParameterizedTest(name = "moviesAndSeriesPerGenre.get({0}).getGenre() == {1}")
        @CsvSource({"0, COMEDY", "1, CRIME", "2, DRAMA", "3, MYSTERY", "4, THRILLER"})
        void ensureGenreFromFindSeriesPerGenre(int position, String genre) {
            // when
            List<MoviesAndSeriesPerGenreDto> moviesAndSeriesPerGenre = seriesRepository.findSeriesPerGenre();

            // then
            assertEquals(moviesAndSeriesPerGenre.get(position).getGenre().toString(), genre);
        }

        @ParameterizedTest(name = "moviesAndSeriesPerGenre.get({0}).getCount() == {2}")
        @CsvSource({"0, 1", "1, 1", "2, 1", "3, 1", "4, 1"})
        void ensureCountFromFindSeriesPerGenre(int position, int count) {
            // when
            List<MoviesAndSeriesPerGenreDto> moviesAndSeriesPerGenre = seriesRepository.findSeriesPerGenre();

            // then
            assertEquals(moviesAndSeriesPerGenre.get(position).getCount(), count);
        }
    }

    private void createBunchOfSeriesAndEpisodes() {
        List<Episode> episodes = List.of(
                Episode.builder()
                        .title(EPISODE_TITLE_001)
                        .summary(SUMMARY_000)
                        .durationInSeconds(3720)
                        .releaseDate(LocalDate.of(2015, 6, 24))
                        .distributor("NBCUniversal Television Distribution")
                        .rating(9.3)
                        .season(1)
                        .build(),
                Episode.builder()
                        .title(EPISODE_TITLE_002)
                        .summary(SUMMARY_001)
                        .durationInSeconds(2880)
                        .releaseDate(LocalDate.of(2015, 7, 1))
                        .distributor("NBCUniversal Television Distribution")
                        .rating(8.6)
                        .season(1)
                        .build(),
                Episode.builder()
                        .title(EPISODE_TITLE_003)
                        .summary(SUMMARY_002)
                        .durationInSeconds(1320)
                        .releaseDate(LocalDate.of(1994, 9, 22))
                        .distributor("National Broadcasting Company (NBC)")
                        .rating(8.3)
                        .season(1)
                        .build(),
                Episode.builder()
                        .title(EPISODE_TITLE_004)
                        .summary(SUMMARY_003)
                        .durationInSeconds(1320)
                        .releaseDate(LocalDate.of(1994, 9, 29))
                        .distributor("National Broadcasting Company (NBC)")
                        .rating(8.0)
                        .season(1)
                        .build()
        );

        List<Series> series = List.of(
                Series.builder()
                        .title(SERIES_TITLE_001)
                        .info(SERIES_INFO_000)
                        .seriesCategory(SeriesCategory.TV_SERIALS)
                        .genres(Set.of(Genre.DRAMA, Genre.MYSTERY, Genre.THRILLER, Genre.CRIME))
                        .episodes(Set.of(episodes.get(0), episodes.get(1)))
                        .build(),
                Series.builder()
                        .title(SERIES_TITLE_002)
                        .info(SERIES_INFO_001)
                        .seriesCategory(SeriesCategory.TV_SERIALS)
                        .genres(Set.of(Genre.COMEDY))
                        .episodes(Set.of(episodes.get(2), episodes.get(3)))
                        .build()
        );

        episodes.get(0).setSeries(series.get(0));
        episodes.get(1).setSeries(series.get(0));
        episodes.get(2).setSeries(series.get(1));
        episodes.get(3).setSeries(series.get(1));

        logger.info("Created {} series.", seriesRepository.saveAll(series).size());
        logger.info("Created {} episodes.", episodeRepository.saveAll(episodes).size());
    }
}