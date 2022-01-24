package gr.sae.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class SmdbRunner implements CommandLineRunner {
    private final PeopleCreatorRunner peopleCreatorRunner;
    private final MovieCreatorRunner movieCreatorRunner;
    private final SeriesCreatorRunner seriesCreatorRunner;
    private final OccupationCreatorRunner occupationCreatorRunner;

    @Override
    public void run(String... args) throws Exception {
        peopleCreatorRunner.createBunchOfPeople();
        movieCreatorRunner.createBunchOfMovies();
        seriesCreatorRunner.createBunchOfSeriesAndEpisodes();
        occupationCreatorRunner.createBunchOfOccupations();
    }
}
