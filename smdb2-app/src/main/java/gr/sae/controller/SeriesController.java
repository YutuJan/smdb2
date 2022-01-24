package gr.sae.controller;

import gr.sae.domain.Episode;
import gr.sae.domain.Genre;
import gr.sae.domain.Series;
import gr.sae.service.BaseService;
import gr.sae.service.EpisodeService;
import gr.sae.service.SeriesService;
import gr.sae.transfer.ApiResponse;
import gr.sae.transfer.SeriesAndEpisodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/series")
public class SeriesController extends AbstractController<Series> {
    private final SeriesService seriesService;
    private final EpisodeService episodeService;

    @GetMapping("/get/{title}")
    public ResponseEntity<ApiResponse<Series>> get(@PathVariable final String title) {
        return ResponseEntity.ok(ApiResponse.<Series>builder().data(seriesService.get(title)).build());
    }

    @GetMapping("/find/{title}")
    public ResponseEntity<ApiResponse<Series>> find(@PathVariable final String title) {
        return ResponseEntity.ok(ApiResponse.<Series>builder().data(seriesService.find(title)).build());
    }

    @DeleteMapping("/delete/{title}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByTitle(@PathVariable("title") final String title) {
        seriesService.deleteByTitle(title);
    }

    @GetMapping(params = "g")
    public ResponseEntity<ApiResponse<List<Series>>> findSeriesByGenresContaining(@RequestParam("g") final String genre) {
        Genre genre1 = Genre.valueOf(genre);
        return ResponseEntity.ok(ApiResponse.<List<Series>>builder().data(seriesService.findByGenre(genre1)).build());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addEpisode(@Valid @RequestBody final SeriesAndEpisodeDto seriesAndEpisodeDto) {
        Series series = seriesAndEpisodeDto.getSeries();
        Episode episode = seriesAndEpisodeDto.getEpisode();

        if (seriesService.exists(series)) {
            seriesService.addEpisode(series, episode);
        }
    }

    @DeleteMapping("/remove/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEpisode(@Valid @RequestBody final SeriesAndEpisodeDto seriesAndEpisodeDto) {
        Series series = seriesAndEpisodeDto.getSeries();
        Episode episode = seriesAndEpisodeDto.getEpisode();

        if (seriesService.exists(series)) {
            seriesService.removeEpisode(series, episode);
        }
    }

    @DeleteMapping("/remove/series_id/{series_id}/episode_id/{episode_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEpisode(@PathVariable("series_id") final Long series_id,
                              @PathVariable("episode_id") final Long episode_id) {
        Series series = seriesService.find(series_id);
        Episode episode = episodeService.find(episode_id);

        if (seriesService.exists(series)) {
            seriesService.removeEpisode(series, episode);
        }
    }

    @DeleteMapping("/remove/series_title/{series_title}/episode_title/{episode_title}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEpisode(@PathVariable("series_title") final String series_title,
                              @PathVariable("episode_title") final String episode_title) {
        Series series = seriesService.find(series_title);
        Episode episode = episodeService.find(episode_title);

        if (seriesService.exists(series)) {
            seriesService.removeEpisode(series, episode);
        }
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEpisode(@Valid @RequestBody final SeriesAndEpisodeDto seriesAndEpisodeDto) {
        Series series = seriesAndEpisodeDto.getSeries();
        Episode episode = seriesAndEpisodeDto.getEpisode();

        if (seriesService.exists(series)) {
            seriesService.updateEpisode(series, episode);
        }
    }

    @Override
    protected BaseService<Series, Long> getService() {
        return seriesService;
    }
}
