package gr.sae.controller;

import gr.sae.domain.*;
import gr.sae.service.*;
import gr.sae.transfer.ApiResponse;
import gr.sae.transfer.MoviesAndSeriesPerGenreDto;
import gr.sae.transfer.MoviesPerGenrePerYearDto;
import gr.sae.transfer.TopRatedMovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportsController extends AbstractController {
    private final MovieService movieService;
    private final EpisodeService episodeService;
    private final SeriesService seriesService;
    private final PersonService personService;

    @Override
    protected BaseService getService() {
        return null;
    }

    @GetMapping("/top/movie")
    public ResponseEntity<ApiResponse<TopRatedMovieDto>> findTopRatedMovie() {
        return ResponseEntity.ok(ApiResponse.<TopRatedMovieDto>builder().data(movieService.findTopRatedMovie()).build());
    }

    @GetMapping("/top/episode")
    public ResponseEntity<ApiResponse<Episode>> findTopRatedEpisode() {
        return ResponseEntity.ok(ApiResponse.<Episode>builder().data(episodeService.findFirstByOrderByRatingDesc()).build());
    }

    @GetMapping(value = "/person", params = {"fn", "ln"})
    public ResponseEntity<ApiResponse<Person>> find(@RequestParam("fn") String firstName,
                                                    @RequestParam("ln") String lastName) {
        return ResponseEntity.ok(ApiResponse.<Person>builder().data(personService.find(firstName, lastName)).build());
    }


    @GetMapping(headers = "a=findContentByGenre", params = "g")
    public ResponseEntity<ApiResponse<List<BaseModel>>> findSeriesByGenresContaining(@RequestParam("g") final String genre) {
        Genre genre1 = Genre.valueOf(genre);
        List<Series> series = seriesService.findByGenre(genre1);
        List<Movie> movies = movieService.findMoviesByGenresContaining(genre1);

        List<BaseModel> newList = new ArrayList<>(series);
        newList.addAll(movies);

        return ResponseEntity.ok(ApiResponse.<List<BaseModel>>builder().data(newList).build());
    }

    @GetMapping(headers = "a=findMoviesPerGenre")
    public ResponseEntity<ApiResponse<List<MoviesAndSeriesPerGenreDto>>> findMoviesPerGenre() {
        return ResponseEntity.ok(ApiResponse.<List<MoviesAndSeriesPerGenreDto>>builder().data(movieService.findMoviesPerGenre()).build());
    }

    @GetMapping(headers = "a=findSeriesPerGenre")
    public ResponseEntity<ApiResponse<List<MoviesAndSeriesPerGenreDto>>> findSeriesPerGenre() {
        return ResponseEntity.ok(ApiResponse.<List<MoviesAndSeriesPerGenreDto>>builder().data(seriesService.findSeriesPerGenre()).build());
    }

    @GetMapping(headers = "a=findMoviesPerGenrePerYear")
    public ResponseEntity<ApiResponse<List<MoviesPerGenrePerYearDto>>> findMoviesPerGenrePerGenre() {
        return ResponseEntity.ok(ApiResponse.<List<MoviesPerGenrePerYearDto>>builder().data(movieService.findMoviesPerGenrePerYear()).build());
    }

}
