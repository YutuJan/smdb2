package gr.sae.service;

import gr.sae.domain.Occupation;
import gr.sae.domain.Person;

public interface PersonService extends BaseService<Person, Long> {
    Person get(String firstName, String lastName);

    Person find(String firstName, String lastName);

    void deleteByName(String firstName, String lastName);

    void addOccupation(Person person, Occupation occupation);

    void removeOccupation(Person person, Occupation occupation);

    void addPersonToMovieOccupation(Long personId, Long movieId, String roleType);

    void addPersonToMovieOccupation(String firstName, String lastName, String title, String roleType);

    void removePersonToMovieOccupation(Long personId, Long movieId, String roleType);

    void removePersonToMovieOccupation(String firstName, String lastName, String title, String roleType);

    void addPersonToEpisodeOccupation(Long personId, Long episodeId, String roleType);

    void addPersonToEpisodeOccupation(String firstName, String lastName, String title, String roleType);

    void removePersonToEpisodeOccupation(Long personId, Long episodeId, String roleType);

    void removePersonToEpisodeOccupation(String firstName, String lastName, String title, String roleType);
}
