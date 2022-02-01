package gr.sae.smdb2.repository;

import gr.sae.base.AbstractLogComponent;
import gr.sae.domain.Person;
import gr.sae.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest extends AbstractLogComponent {
    @Autowired
    private PersonRepository personRepository;

    private final String BIO_000 = "After first establishing his martial arts prowess in his native Hong Kong," +
            " actor-choreographer-director Jackie Chan took his massive success in Southeast Asia and became a" +
            " huge international star, particularly in America. With a reputation as an unrelenting performer" +
            " willing to risk bodily injury - both with himself and his fellow stuntmen - to create elaborate" +
            " and jaw-dropping action sequences, Chan amazed critics and audiences with his sheer technical skill" +
            " while redefining Hong Kong action movies by bringing in an element of comedy. He spent the first" +
            " couple of decades finding his footing, but had a major breakthrough with the action-comedy \"Snake" +
            " in the Eagle's Shadow\" (1978), which propelled the previously-struggling performer into the" +
            " limelight. Though he took a shot at Hollywood with \"Battle Creek Brawl\" (1980) and \"The Cannonball" +
            " Run\" (1981), he would have to wait until \"Rumble in the Bronx\" (1996) to make his mark in the" +
            " United States. But it was his starring turn in the wildly popular \"Rush Hour\" (1998) and its" +
            " sequels that cemented his place as one of Hollywood's elite action stars. His status as a bankable" +
            " actor was further enhanced with \"Shanghai Noon\" (2001) and its follow-up, \"Shanghai Knights\"" +
            " (2003), though he took a stumble with \"Around the World in 80 Days\" (2004). While he returned" +
            " to Hong Kong for a number of films, including his first with Jet Li, \"The Forbidden Kingdom\"" +
            " (2008), Chan remained busy in Hollywood, as he retained his hold on being a popular box office draw.";
    private final String BIO_001 = "Well-known for his trademark rapid-fire wisecracks, actor-comedian Chris" +
            " Tucker broke into movies in the mid-1990s, following a successful career in stand-up. After his" +
            " scene-stealing turn in director F. Gary Gray's 1995 cult comedy hit \"Friday,\" Tucker took on" +
            " the role of his career as Jackie Chan's comic foil and high-pitched partner in the action-comedy," +
            " \"Rush Hour\" (1998). Thanks to the worldwide success of \"Rush Hour,\" Tucker became the fastest" +
            " actor ever to make Hollywood's elite \"$20 Million Club\" - the princely sum he received for" +
            " appearing in the 2001 sequel. The comedian's star power remained significant enough to once again" +
            " command a $20 million payday for the inevitable sequel, \"Rush Hour 3\" (2007). Tucker laid low" +
            " following its modest success for a couple of years, but reemerged in 2011 to reignite his stand-up" +
            " career, reminding fans of what he had always done best.";
    private final String BIO_002 = "The tempestuous screen image of two-time Academy Award winner and Renaissance" +
            " man Anthony Quinn at times seemed to mirror the prolific actor's much publicized, unquenchable" +
            " thirst for life. His exotic background enabled him to play a nearly limitless variety of ethnic" +
            " characters, ranging from Crazy Horse in \"They Died with Their Boots On\" (1942), to the marauding" +
            " Mongol warrior in \"Attila\" (1955), to an Eskimo in \"The Savage Innocents\" (1961). An" +
            " accomplished artist and painter in his own right, it came as no surprise when he embraced the role" +
            " of impressionist Paul Gauguin in \"Lust for Life\" (1956), a role that won him his second Oscar." +
            " It was, however, for his embodiment of the garrulous \"Zorba the Greek\" (1964) that Quinn would" +
            " be forever remembered, so perfectly did he capture the free-spirited, unrestrained nature of the" +
            " irascible character. Incredibly prolific, he continued to work steadily over the decades, appearing" +
            " in such films as \"The Greek Tycoon\" (1978) and the telepic adaptation of \"Ernest Hemingway's" +
            " The Old Man and the Sea\" (NBC, 1990). A man of deep appetites and diverse passions, both in film" +
            " and in his own life, Anthony Quinn became one of cinema's most beloved and respected actors in a" +
            " career that spanned nearly 70 years and more than a 150 memorable performances. ";

    @BeforeEach
    void setUp() {
        createBunchOfPeople();
    }

    @Test
    void ensureGetPersonByFirstNameAndLastName() {
        // given
        String firstName = "Chris";
        String lastName = "Tucker";

        // when
        Person person = personRepository.getPersonByFirstNameAndLastName(firstName, lastName);

        // then
        assertAll("Ensure that the repository gets the right person.",
                () -> assertNotNull(person, "Repository must not return a null value."),
                () -> assertEquals(firstName, person.getFirstName(), "First name must be " + firstName + "."),
                () -> assertEquals(lastName, person.getLastName(), "Last name must be " + lastName + "."));
    }

    @Test
    void ensureFindPersonByFirstNameAndLastName() {
        // given
        String firstName = "Chris";
        String lastName = "Tucker";

        // when
        Person person = personRepository.findPersonByFirstNameAndLastName(firstName, lastName);

        // then
        assertAll("Ensure that the repository finds the right person.",
                () -> assertNotNull(person, "Repository must not return a null value."),
                () -> assertEquals(firstName, person.getFirstName(), "First name must be " + firstName + "."),
                () -> assertEquals(lastName, person.getLastName(), "Last name must be " + lastName + "."));
    }

    private void createBunchOfPeople() {
        List<Person> people = List.of(
                Person.builder().firstName("Jackie").lastName("Chan")
                        .birthDate(LocalDate.of(1954, 4, 7))
                        .birthPlace("Victoria Peak, Hong Kong")
                        .biography(BIO_000)
                        .build(),
                Person.builder().firstName("Chris").lastName("Tucker")
                        .birthDate(LocalDate.of(1972, 8, 31))
                        .birthPlace("Atlanta, Georgia, USA")
                        .biography(BIO_001)
                        .build(),
                Person.builder().firstName("Anthony").lastName("Quinn")
                        .birthDate(LocalDate.of(1915, 4, 21))
                        .birthPlace("Chihuahua, Mexico")
                        .biography(BIO_002)
                        .build()
        );

        logger.info("Created {} products.", personRepository.saveAll(people).size());
    }
}