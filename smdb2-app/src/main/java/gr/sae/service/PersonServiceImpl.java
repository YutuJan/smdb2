package gr.sae.service;

import gr.sae.domain.*;
import gr.sae.repository.OccupationRepository;
import gr.sae.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {
    private final PersonRepository personRepository;
    private final OccupationRepository occupationRepository;
    private MovieService movieService;
    private EpisodeService episodeService;

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @Autowired
    public void setEpisodeService(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @Override
    JpaRepository<Person, Long> getRepository() {
        return personRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void delete(Person entity) {
        Person person = find(entity.getFirstName(), entity.getLastName());
        Set<Occupation> occupations = new HashSet<>(person.getOccupations());

        for (Occupation o : occupations) {
            Movie movie = movieService.find(o.getVideoEntertainment().getTitle());
            Episode episode = episodeService.find(o.getVideoEntertainment().getTitle());
            if (movie != null && episode == null) {
                removeOccupation(person, movie, o);
            }
            if (movie == null && episode != null) {
                removeOccupation(person, episode, o);
            }
        }

        super.delete(person);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Person person = find(id);
        Set<Occupation> occupations = new HashSet<>(person.getOccupations());

        for (Occupation o : occupations) {
            Movie movie = movieService.find(o.getVideoEntertainment().getTitle());
            Episode episode = episodeService.find(o.getVideoEntertainment().getTitle());
            if (movie != null && episode == null) {
                removeOccupation(person, movie, o);
            }
            if (movie == null && episode != null) {
                removeOccupation(person, episode, o);
            }
        }

        super.delete(person);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void deleteByName(String firstName, String lastName) {
        Person person = find(firstName, lastName);
        Set<Occupation> occupations = new HashSet<>(person.getOccupations());

        for (Occupation o : occupations) {
            Movie movie = movieService.find(o.getVideoEntertainment().getTitle());
            Episode episode = episodeService.find(o.getVideoEntertainment().getTitle());
            if (movie != null && episode == null) {
                removeOccupation(person, movie, o);
            }
            if (movie == null && episode != null) {
                removeOccupation(person, episode, o);
            }
        }

        super.delete(person);
    }

    @Override
    public Person get(String firstName, String lastName) {
        return personRepository.getPersonByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Person find(String firstName, String lastName) {
        return personRepository.findPersonByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public void addPersonToMovieOccupation(Long personId, Long movieId, String roleType) {
        Person person = find(personId);
        Movie movie = movieService.find(movieId);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(movie)
                .occupation(RoleType.valueOf(roleType))
                .build();

        addOccupation(person, movie, occupation);
    }

    @Override
    public void addPersonToMovieOccupation(String firstName, String lastName, String title, String roleType) {
        Person person = find(firstName, lastName);
        Movie movie = movieService.find(title);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(movie)
                .occupation(RoleType.valueOf(roleType))
                .build();

        addOccupation(person, movie, occupation);
    }

    @Override
    public void removePersonToMovieOccupation(Long personId, Long movieId, String roleType) {
        Person person = find(personId);
        Movie movie = movieService.find(movieId);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(movie)
                .occupation(RoleType.valueOf(roleType))
                .build();

        removeOccupation(person, movie, occupation);
    }

    @Override
    public void removePersonToMovieOccupation(String firstName, String lastName, String title, String roleType) {
        Person person = find(firstName, lastName);
        Movie movie = movieService.find(title);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(movie)
                .occupation(RoleType.valueOf(roleType))
                .build();

        removeOccupation(person, movie, occupation);
    }

    @Override
    public void addPersonToEpisodeOccupation(Long personId, Long episodeId, String roleType) {
        Person person = find(personId);
        Episode episode = episodeService.find(episodeId);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(episode)
                .occupation(RoleType.valueOf(roleType))
                .build();

        addOccupation(person, episode, occupation);
    }

    @Override
    public void addPersonToEpisodeOccupation(String firstName, String lastName, String title, String roleType) {
        Person person = find(firstName, lastName);
        Episode episode = episodeService.find(title);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(episode)
                .occupation(RoleType.valueOf(roleType))
                .build();

        addOccupation(person, episode, occupation);
    }

    @Override
    public void removePersonToEpisodeOccupation(Long personId, Long episodeId, String roleType) {
        Person person = find(personId);
        Episode episode = episodeService.find(episodeId);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(episode)
                .occupation(RoleType.valueOf(roleType))
                .build();

        removeOccupation(person, episode, occupation);
    }

    @Override
    public void removePersonToEpisodeOccupation(String firstName, String lastName, String title, String roleType) {
        Person person = find(firstName, lastName);
        Episode episode = episodeService.find(title);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(episode)
                .occupation(RoleType.valueOf(roleType))
                .build();

        removeOccupation(person, episode, occupation);
    }

    @Override
    public void addOccupation(Person person, Occupation occupation) {
        if (isNull(occupation) || isNull(person)) {
            return;
        }

        person.addOccupation(occupation);
        update(person);

        logger.debug("Occupation[{}] added to Person[{}]", occupation, person);
    }

    @Override
    public void removeOccupation(Person person, Occupation occupation) {
        if (isNull(occupation) || isNull(person)) {
            return;
        }

        person.removeOccupation(occupation);
        update(person);

        logger.debug("Occupation[{}] update to Person[{}]", occupation, person);
    }

    private void addOccupation(Person person, Movie movie, Occupation occupation) {
        occupationRepository.save(occupation);
        addOccupation(person, occupation);
        movieService.addOccupation(movie, occupation);
    }

    private void removeOccupation(Person person, Movie movie, Occupation occupation) {
        removeOccupation(person, occupation);
        movieService.removeOccupation(movie, occupation);
        occupationRepository.delete(occupation);
    }

    private void addOccupation(Person person, Episode episode, Occupation occupation) {
        occupationRepository.save(occupation);
        addOccupation(person, occupation);
        episodeService.addOccupation(episode, occupation);
    }

    private void removeOccupation(Person person, Episode episode, Occupation occupation) {
        episodeService.removeOccupation(episode, occupation);
        removeOccupation(person, occupation);
        occupationRepository.delete(occupation);
    }

    private boolean isNull(Object object) {
        return object == null;
    }
}
