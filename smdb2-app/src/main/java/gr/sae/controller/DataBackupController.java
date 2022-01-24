package gr.sae.controller;

import gr.sae.report.BackupManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/backup")
public class DataBackupController {
    private final BackupManager backupManager;

    @GetMapping("/export/csv")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exportEverythingToCSV() {
        backupManager.exportEverythingToCSV();
    }

    @GetMapping("/export/csv/people")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exportPeopleToCSV() {
        backupManager.exportPeopleToCSV();
    }

    @GetMapping("/export/csv/movies")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exportMovieToCSV() {
        backupManager.exportMoviesToCSV();
    }

    @GetMapping("/export/csv/series")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exportSeriesToCSV() {
        backupManager.exportSeriesToCSV();
    }

    @GetMapping("/export/csv/episodes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exportEpisodesToCSV() {
        backupManager.exportEpisodesToCSV();
    }
}
