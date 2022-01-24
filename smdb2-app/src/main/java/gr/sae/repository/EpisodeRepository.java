package gr.sae.repository;

import gr.sae.domain.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    Episode findEpisodeByTitle(String title);

    Episode getEpisodeByTitle(String title);

    Episode findFirstByOrderByRatingDesc();
}
