package gr.sae.controller;

import gr.sae.domain.Person;
import gr.sae.service.BaseService;
import gr.sae.service.PersonService;
import gr.sae.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController extends AbstractController<Person> {
    private final PersonService personService;

    @GetMapping(value = "/find", params = {"fn", "ln"})
    public ResponseEntity<ApiResponse<Person>> find(@RequestParam("fn") String firstName,
                                                    @RequestParam("ln") String lastName) {
        return ResponseEntity.ok(ApiResponse.<Person>builder().data(personService.find(firstName, lastName)).build());
    }

    @GetMapping(value = "/get", params = {"fn", "ln"})
    public ResponseEntity<ApiResponse<Person>> get(@RequestParam("fn") String firstName,
                                                   @RequestParam("ln") String lastName) {
        return ResponseEntity.ok(ApiResponse.<Person>builder().data(personService.get(firstName, lastName)).build());
    }

    @DeleteMapping("/delete/person_fn/{person_fn}/person_ln/{person_ln}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByName(@PathVariable("person_fn") final String firstName,
                             @PathVariable("person_ln") final String lastName) {
        personService.deleteByName(firstName, lastName);
    }

    @PostMapping("/person_id/{person_id}/movie_id/{movie_id}/role_type/{role_type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPersonToMovieOccupation(@PathVariable("person_id") final Long personId,
                                           @PathVariable("movie_id") final Long movieId,
                                           @PathVariable("role_type") final String roleType) {
        personService.addPersonToMovieOccupation(personId, movieId, roleType);
    }

    @DeleteMapping("/person_id/{person_id}/movie_id/{movie_id}/role_type/{role_type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePersonToMovieOccupation(@PathVariable("person_id") final Long personId,
                                              @PathVariable("movie_id") final Long movieId,
                                              @PathVariable("role_type") final String roleType) {
        personService.removePersonToMovieOccupation(personId, movieId, roleType);
    }

    @PostMapping("/person_id/{person_id}/episode_id/{episode_id}/role_type/{role_type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPersonToEpisodeOccupation(@PathVariable("person_id") final Long personId,
                                             @PathVariable("episode_id") final Long episodeId,
                                             @PathVariable("role_type") final String roleType) {
        personService.addPersonToEpisodeOccupation(personId, episodeId, roleType);
    }

    @DeleteMapping("/person_id/{person_id}/episode_id/{episode_id}/role_type/{role_type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePersonToEpisodeOccupation(@PathVariable("person_id") final Long personId,
                                                @PathVariable("episode_id") final Long episodeId,
                                                @PathVariable("role_type") final String roleType) {
        personService.removePersonToEpisodeOccupation(personId, episodeId, roleType);
    }

    @PostMapping("/person_fn/{person_fn}/person_ln/{person_ln}/movie_title/{movie_title}/role_type/{role_type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPersonToMovieOccupation(@PathVariable("person_fn") final String firstName,
                                           @PathVariable("person_ln") final String lastName,
                                           @PathVariable("movie_title") final String title,
                                           @PathVariable("role_type") final String roleType) {
        personService.addPersonToMovieOccupation(firstName, lastName, title, roleType);
    }

    @DeleteMapping("/person_fn/{person_fn}/person_ln/{person_ln}/movie_title/{movie_title}/role_type/{role_type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePersonToMovieOccupation(@PathVariable("person_fn") final String firstName,
                                              @PathVariable("person_ln") final String lastName,
                                              @PathVariable("movie_title") final String title,
                                              @PathVariable("role_type") final String roleType) {
        personService.removePersonToMovieOccupation(firstName, lastName, title, roleType);
    }

    @PostMapping("/person_fn/{person_fn}/person_ln/{person_ln}/episode_title/{episode_title}/role_type/{role_type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPersonToEpisodeOccupation(@PathVariable("person_fn") final String firstName,
                                             @PathVariable("person_ln") final String lastName,
                                             @PathVariable("episode_title") final String title,
                                             @PathVariable("role_type") final String roleType) {
        personService.addPersonToEpisodeOccupation(firstName, lastName, title, roleType);
    }

    @DeleteMapping("/person_fn/{person_fn}/person_ln/{person_ln}/episode_title/{episode_title}/role_type/{role_type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePersonToEpisodeOccupation(@PathVariable("person_fn") final String firstName,
                                                @PathVariable("person_ln") final String lastName,
                                                @PathVariable("episode_title") final String title,
                                                @PathVariable("role_type") final String roleType) {
        personService.removePersonToEpisodeOccupation(firstName, lastName, title, roleType);
    }

    @Override
    protected BaseService<Person, Long> getService() {
        return personService;
    }
}
