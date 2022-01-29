package gr.sae.service;

import gr.sae.domain.*;
import gr.sae.repository.MovieRepository;
import gr.sae.repository.OccupationRepository;
import gr.sae.transfer.MoviesAndSeriesPerGenreDto;
import gr.sae.transfer.MoviesPerGenrePerYearDto;
import gr.sae.transfer.TopRatedMovieDto;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {
    private final MovieRepository movieRepository;
    private final OccupationRepository occupationRepository;
    private PersonService personService;

    @Autowired
    @Lazy
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    JpaRepository<Movie, Long> getRepository() {
        return movieRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void delete(Movie entity) {
        Movie movie = find(entity.getTitle());
        Set<Occupation> occupations = new HashSet<>(movie.getOccupations());

        for (Occupation o : occupations) {
            removeOccupation(o.getPerson(), movie, o);
        }

        super.delete(movie);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Movie movie = find(id);
        Set<Occupation> occupations = new HashSet<>(movie.getOccupations());

        for (Occupation o : occupations) {
            removeOccupation(o.getPerson(), movie, o);
        }

        super.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void deleteByTitle(String title) {
        Movie movie = find(title);
        Set<Occupation> occupations = new HashSet<>(movie.getOccupations());

        for (Occupation o : occupations) {
            removeOccupation(o.getPerson(), movie, o);
        }

        super.delete(movie);
    }

    @Override
    public Movie get(String title) {
        return movieRepository.getMovieByTitle(title);
    }

    @Override
    public Movie find(String title) {
        return movieRepository.findMovieByTitle(title);
    }

    @Override
    public void addPersonToMovieOccupation(Long personId, Long movieId, String roleType) {
        Person person = personService.find(personId);
        Movie movie = find(movieId);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(movie)
                .occupation(RoleType.valueOf(roleType))
                .build();

        addOccupation(person, movie, occupation);
    }

    @Override
    public void addPersonToMovieOccupation(String firstName, String lastName, String title, String roleType) {
        Person person = personService.find(firstName, lastName);
        Movie movie = find(title);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(movie)
                .occupation(RoleType.valueOf(roleType))
                .build();

        addOccupation(person, movie, occupation);
    }

    @Override
    public void removePersonToMovieOccupation(Long personId, Long movieId, String roleType) {
        Person person = personService.find(personId);
        Movie movie = find(movieId);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(movie)
                .occupation(RoleType.valueOf(roleType))
                .build();

        removeOccupation(person, movie, occupation);
    }

    @Override
    public void removePersonToMovieOccupation(String firstName, String lastName, String title, String roleType) {
        Person person = personService.find(firstName, lastName);
        Movie movie = find(title);
        Occupation occupation = Occupation.builder()
                .person(person)
                .videoEntertainment(movie)
                .occupation(RoleType.valueOf(roleType))
                .build();

        removeOccupation(person, movie, occupation);
    }

    @Override
    public List<Movie> findMoviesByRatingIsGreaterThanEqual(Double rating) {
        return movieRepository.findMoviesByRatingIsGreaterThanEqual(rating);
    }

    @Override
    public TopRatedMovieDto findTopRatedMovie() {
        return movieRepository.findTopRatedMovie();
    }

    @Override
    public List<Movie> findMoviesByGenresContaining(Genre genre) {
        return movieRepository.findMoviesByGenresContaining(genre);
    }

    @Override
    public List<MoviesAndSeriesPerGenreDto> findMoviesPerGenre() {
        return movieRepository.findMoviesPerGenre();
    }

    @Override
    public List<MoviesPerGenrePerYearDto> findMoviesPerGenrePerYear() {
        return movieRepository.findMoviesPerGenrePerYear();
    }

    @Override
    public void addOccupation(Movie movie, Occupation occupation) {
        if (isNull(occupation) || isNull(movie)) {
            return;
        }

        movie.addOccupation(occupation);

        logger.debug("Occupation[{}] added to Movie[{}]", occupation, movie);
    }

    @Override
    public void removeOccupation(Movie movie, Occupation occupation) {
        if (isNull(occupation) || isNull(movie)) {
            return;
        }

        movie.removeOccupation(occupation);
        update(movie);

        logger.debug("Occupation[{}] removed to Movie[{}]", occupation, movie);
    }

    private void addOccupation(Person person, Movie movie, Occupation occupation) {
        occupationRepository.save(occupation);
        personService.addOccupation(person, occupation);
        addOccupation(movie, occupation);
    }

    private void removeOccupation(Person person, Movie movie, Occupation occupation) {
        personService.removeOccupation(person, occupation);
        removeOccupation(movie, occupation);
    }

    private boolean isNull(Object object) {
        return object == null;
    }
}
