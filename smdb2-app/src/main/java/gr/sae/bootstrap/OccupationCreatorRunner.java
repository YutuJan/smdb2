package gr.sae.bootstrap;

import gr.sae.base.AbstractLogComponent;
import gr.sae.domain.RoleType;
import gr.sae.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class OccupationCreatorRunner extends AbstractLogComponent implements CommandLineRunner {
    private final PersonService personService;

    private final String PERSON1_FIRST_NAME = "Jackie";
    private final String PERSON2_FIRST_NAME = "Chris";
    private final String PERSON3_FIRST_NAME = "Anthony";
    private final String PERSON4_FIRST_NAME = "Keanu";
    private final String PERSON5_FIRST_NAME = "Carrie-Anne";
    private final String PERSON6_FIRST_NAME = "Laurence";
    private final String PERSON7_FIRST_NAME = "Arnold";
    private final String PERSON8_FIRST_NAME = "Linda";
    private final String PERSON9_FIRST_NAME = "Milla";
    private final String PERSON10_FIRST_NAME = "Bruce";
    private final String PERSON11_FIRST_NAME = "Sylvester";
    private final String PERSON12_FIRST_NAME = "Dolph";
    private final String PERSON13_FIRST_NAME = "Jeff";
    private final String PERSON14_FIRST_NAME = "Julianne";
    private final String PERSON15_FIRST_NAME = "Rami";
    private final String PERSON16_FIRST_NAME = "Jennifer";
    private final String PERSON17_FIRST_NAME = "Courteney";
    private final String PERSON18_FIRST_NAME = "Jim";
    private final String PERSON19_FIRST_NAME = "Jeff";
    private final String PERSON20_FIRST_NAME = "Lucy";

    private final String PERSON1_LAST_NAME = "Chan";
    private final String PERSON2_LAST_NAME = "Tucker";
    private final String PERSON3_LAST_NAME = "Quinn";
    private final String PERSON4_LAST_NAME = "Reeves";
    private final String PERSON5_LAST_NAME = "Moss";
    private final String PERSON6_LAST_NAME = "Fishburne";
    private final String PERSON7_LAST_NAME = "Schwarzenegger";
    private final String PERSON8_LAST_NAME = "Hamilton";
    private final String PERSON9_LAST_NAME = "Jovovich";
    private final String PERSON10_LAST_NAME = "Willis";
    private final String PERSON11_LAST_NAME = "Stallone";
    private final String PERSON12_LAST_NAME = "Lundgren";
    private final String PERSON13_LAST_NAME = "Bridges";
    private final String PERSON14_LAST_NAME = "Moore";
    private final String PERSON15_LAST_NAME = "Malek";
    private final String PERSON16_LAST_NAME = "Aniston";
    private final String PERSON17_LAST_NAME = "Cox";
    private final String PERSON18_LAST_NAME = "Carrey";
    private final String PERSON19_LAST_NAME = "Daniels";
    private final String PERSON20_LAST_NAME = "Boynton";

    private final String MOVIE1_TITLE = "The Matrix";
    private final String MOVIE2_TITLE = "Zorba the Greek";
    private final String MOVIE3_TITLE = "Rush Hour";
    private final String MOVIE4_TITLE = "The Terminator";
    private final String MOVIE5_TITLE = "The Expendables";
    private final String MOVIE6_TITLE = "Rocky";
    private final String MOVIE7_TITLE = "The Big Lebowski";
    private final String MOVIE8_TITLE = "Ace Ventura: Pet Detective";
    private final String MOVIE9_TITLE = "Dumb & Dumber";
    private final String MOVIE10_TITLE = "Bohemian Rhapsody";

    private final String EPISODE1_TITLE = "eps1.0_hellofriend.mov";
    private final String EPISODE2_TITLE = "eps1.1_ones-and-zer0es.mpeg";
    private final String EPISODE3_TITLE = "The One Where Monica Gets a Roommate";
    private final String EPISODE4_TITLE = "The One with the Sonogram at the End";

    @Override
    public void run(String... args) throws Exception {
    }

    public void createBunchOfOccupations() {
        personService.addPersonToMovieOccupation(
                PERSON1_FIRST_NAME, PERSON1_LAST_NAME, MOVIE3_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON2_FIRST_NAME, PERSON2_LAST_NAME, MOVIE3_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON3_FIRST_NAME, PERSON3_LAST_NAME, MOVIE2_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON4_FIRST_NAME, PERSON4_LAST_NAME, MOVIE1_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON5_FIRST_NAME, PERSON5_LAST_NAME, MOVIE1_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON6_FIRST_NAME, PERSON6_LAST_NAME, MOVIE1_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON7_FIRST_NAME, PERSON7_LAST_NAME, MOVIE4_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON8_FIRST_NAME, PERSON8_LAST_NAME, MOVIE4_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON1_FIRST_NAME, PERSON1_LAST_NAME, MOVIE5_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON7_FIRST_NAME, PERSON7_LAST_NAME, MOVIE5_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON9_FIRST_NAME, PERSON9_LAST_NAME, MOVIE5_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON10_FIRST_NAME, PERSON10_LAST_NAME, MOVIE5_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON11_FIRST_NAME, PERSON11_LAST_NAME, MOVIE5_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON12_FIRST_NAME, PERSON12_LAST_NAME, MOVIE5_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON13_FIRST_NAME, PERSON13_LAST_NAME, MOVIE7_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON14_FIRST_NAME, PERSON14_LAST_NAME, MOVIE7_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON15_FIRST_NAME, PERSON15_LAST_NAME, MOVIE10_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON20_FIRST_NAME, PERSON20_LAST_NAME, MOVIE10_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON18_FIRST_NAME, PERSON18_LAST_NAME, MOVIE9_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON19_FIRST_NAME, PERSON19_LAST_NAME, MOVIE9_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON11_FIRST_NAME, PERSON11_LAST_NAME, MOVIE6_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON12_FIRST_NAME, PERSON12_LAST_NAME, MOVIE6_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToMovieOccupation(
                PERSON18_FIRST_NAME, PERSON18_LAST_NAME, MOVIE8_TITLE, String.valueOf(RoleType.ACTOR));


        personService.addPersonToEpisodeOccupation(
                PERSON15_FIRST_NAME, PERSON15_LAST_NAME, EPISODE1_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToEpisodeOccupation(
                PERSON15_FIRST_NAME, PERSON15_LAST_NAME, EPISODE2_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToEpisodeOccupation(
                PERSON16_FIRST_NAME, PERSON16_LAST_NAME, EPISODE3_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToEpisodeOccupation(
                PERSON17_FIRST_NAME, PERSON17_LAST_NAME, EPISODE3_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToEpisodeOccupation(
                PERSON16_FIRST_NAME, PERSON16_LAST_NAME, EPISODE4_TITLE, String.valueOf(RoleType.ACTOR));
        personService.addPersonToEpisodeOccupation(
                PERSON17_FIRST_NAME, PERSON17_LAST_NAME, EPISODE4_TITLE, String.valueOf(RoleType.ACTOR));
    }
}
