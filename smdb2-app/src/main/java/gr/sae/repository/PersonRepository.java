package gr.sae.repository;

import gr.sae.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person getPersonByFirstNameAndLastName(String firstName, String lastName);

    Person findPersonByFirstNameAndLastName(String firstName, String lastName);
}
