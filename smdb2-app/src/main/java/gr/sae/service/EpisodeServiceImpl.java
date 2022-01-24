package gr.sae.service;

import gr.sae.domain.*;
import gr.sae.repository.EpisodeRepository;
import gr.sae.repository.OccupationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EpisodeServiceImpl extends BaseServiceImpl<Episode> implements EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final OccupationRepository occupationRepository;
    private PersonService personService;
    private SeriesService seriesService;

    @Autowired
    @Lazy
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    @Lazy
    public void setSeriesService(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    JpaRepository<Episode, Long> getRepository() {
        return episodeRepository;
    }

    @Override
    public Episode get(String title) {
        return episodeRepository.getEpisodeByTitle(title);
    }

    @Override
    public Episode find(String title) {
        return episodeRepository.findEpisodeByTitle(title);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void delete(Episode episode) {
        Series series = episode.getSeries();

        seriesService.removeEpisode(series, episode);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Episode episode = find(id);
        Series series = episode.getSeries();

        seriesService.removeEpisode(series, episode);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void deleteByTitle(String title) {
        Episode episode = find(title);
        Series series = episode.getSeries();
        System.out.println("Series: " + series);

        seriesService.removeEpisode(series, episode);
    }

    @Override
    public void deleteOccupations(Episode episode) {
        Set<Occupation> occupations = new HashSet<>(episode.getOccupations());

        for (Occupation o : occupations) {
            removeOccupation(o.getPerson(), episode, o);
        }
    }

    @Override
    public void addPersonToEpisodeOccupation(Long personId, Long episodeId, String roleType) {
        Person person = personService.find(personId);
        Episode episode = find(episodeId);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(episode)
                .occupation(RoleType.valueOf(roleType))
                .build();

        addOccupation(person, episode, occupation);
    }

    @Override
    public void addPersonToEpisodeOccupation(String firstName, String lastName, String title, String roleType) {
        Person person = personService.find(firstName, lastName);
        Episode episode = find(title);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(episode)
                .occupation(RoleType.valueOf(roleType))
                .build();

        addOccupation(person, episode, occupation);
    }

    @Override
    public void removePersonToEpisodeOccupation(Long personId, Long episodeId, String roleType) {
        Person person = personService.find(personId);
        Episode episode = find(episodeId);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(episode)
                .occupation(RoleType.valueOf(roleType))
                .build();

        removeOccupation(person, episode, occupation);
    }

    @Override
    public void removePersonToEpisodeOccupation(String firstName, String lastName, String title, String roleType) {
        Person person = personService.find(firstName, lastName);
        Episode episode = find(title);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(episode)
                .occupation(RoleType.valueOf(roleType))
                .build();

        removeOccupation(person, episode, occupation);
    }

    @Override
    public Episode findFirstByOrderByRatingDesc() {
        return episodeRepository.findFirstByOrderByRatingDesc();
    }

    @Override
    public void addOccupation(Episode episode, Occupation occupation) {
        if (isNull(occupation) || isNull(episode)) {
            return;
        }

        episode.addOccupation(occupation);
        update(episode);

        logger.debug("Occupation[{}] added to Episode[{}]", occupation, episode);
    }

    @Override
    public void removeOccupation(Episode episode, Occupation occupation) {
        if (isNull(occupation) || isNull(episode)) {
            return;
        }

        episode.removeOccupation(occupation);
        update(episode);

        logger.debug("Occupation[{}] removed to Episode[{}]", occupation, episode);
    }

    private void addOccupation(Person person, Episode episode, Occupation occupation) {
        occupationRepository.save(occupation);
        personService.addOccupation(person, occupation);
        addOccupation(episode, occupation);
    }

    private void removeOccupation(Person person, Episode episode, Occupation occupation) {
        personService.removeOccupation(person, occupation);
        removeOccupation(episode, occupation);
    }

    private boolean isNull(Object object) {
        return object == null;
    }
}
