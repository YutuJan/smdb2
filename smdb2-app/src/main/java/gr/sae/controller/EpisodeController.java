package gr.sae.controller;

import gr.sae.domain.Episode;
import gr.sae.service.BaseService;
import gr.sae.service.EpisodeService;
import gr.sae.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/episodes")
public class EpisodeController extends AbstractController<Episode> {
    private final EpisodeService episodeService;

    @GetMapping("/get/{title}")
    public ResponseEntity<ApiResponse<Episode>> get(@PathVariable final String title) {
        return ResponseEntity.ok(ApiResponse.<Episode>builder().data(episodeService.get(title)).build());
    }

    @GetMapping("/find/{title}")
    public ResponseEntity<ApiResponse<Episode>> find(@PathVariable final String title) {
        return ResponseEntity.ok(ApiResponse.<Episode>builder().data(episodeService.find(title)).build());
    }

    @DeleteMapping("/delete/{title}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByTitle(@PathVariable("title") final String title) {
        episodeService.deleteByTitle(title);
    }

    @PostMapping("/person_id/{person_id}/episode_id/{episode_id}/role_type/{role_type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPersonToEpisodeOccupation(@PathVariable("person_id") final Long personId,
                                             @PathVariable("episode_id") final Long episodeId,
                                             @PathVariable("role_type") final String roleType) {
        episodeService.addPersonToEpisodeOccupation(personId, episodeId, roleType);
    }

    @DeleteMapping("/person_id/{person_id}/episode_id/{episode_id}/role_type/{role_type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePersonToEpisodeOccupation(@PathVariable("person_id") final Long personId,
                                                @PathVariable("episode_id") final Long episodeId,
                                                @PathVariable("role_type") final String roleType) {
        episodeService.removePersonToEpisodeOccupation(personId, episodeId, roleType);
    }

    @PostMapping("/person_fn/{person_fn}/person_ln/{person_ln}/episode_title/{episode_title}/role_type/{role_type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPersonToEpisodeOccupation(@PathVariable("person_fn") final String firstName,
                                             @PathVariable("person_ln") final String lastName,
                                             @PathVariable("episode_title") final String title,
                                             @PathVariable("role_type") final String roleType) {
        episodeService.addPersonToEpisodeOccupation(firstName, lastName, title, roleType);
    }

    @DeleteMapping("/person_fn/{person_fn}/person_ln/{person_ln}/episode_title/{episode_title}/role_type/{role_type}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePersonToEpisodeOccupation(@PathVariable("person_fn") final String firstName,
                                                @PathVariable("person_ln") final String lastName,
                                                @PathVariable("episode_title") final String title,
                                                @PathVariable("role_type") final String roleType) {
        episodeService.removePersonToEpisodeOccupation(firstName, lastName, title, roleType);
    }

    @Override
    protected BaseService<Episode, Long> getService() {
        return episodeService;
    }
}
