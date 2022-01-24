package gr.sae.report;

import gr.sae.domain.Episode;
import gr.sae.domain.Movie;
import gr.sae.domain.Person;
import gr.sae.domain.Series;
import gr.sae.service.EpisodeService;
import gr.sae.service.MovieService;
import gr.sae.service.PersonService;
import gr.sae.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BackupManagerImpl implements BackupManager {
    private final PersonService personService;
    private final MovieService movieService;
    private final SeriesService seriesService;
    private final EpisodeService episodeService;
    private final String DIRECTORY = "./backups/";

    @Override
    public void exportEverythingToCSV() {
        exportPeopleToCSV();
        exportMoviesToCSV();
        exportSeriesToCSV();
        exportEpisodesToCSV();
    }

    @Override
    public void exportPeopleToCSV() {
        List<Person> bunchOfPeople = personService.findAll();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String timeStamp = dateFormat.format(new Date());
        String fileName = "People_" + timeStamp + ".csv";
        String[] header = {"ID", "FIRSTNAME", "LASTNAME", "BIRTHDATE", "BIRTHPLACE", "BIOGRAPHY", "OCCUPATIONS"};

        createBackupDirectory();

        ICsvBeanWriter csvBeanWriter = null;
        try {
            csvBeanWriter = new CsvBeanWriter(new FileWriter(DIRECTORY + fileName),
                    CsvPreference.STANDARD_PREFERENCE);

            csvBeanWriter.writeHeader(header);
            for (Person p : bunchOfPeople) {
                csvBeanWriter.write(p, header);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        try {
            csvBeanWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportMoviesToCSV() {
        List<Movie> bunchOfMovies = movieService.findAll();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String timeStamp = dateFormat.format(new Date());
        String fileName = "Movies_" + timeStamp + ".csv";
        String[] header = {"ID", "TITLE", "SUMMARY", "DURATIONINSECONDS", "RELEASEDATE", "RATING",
                "DISTRIBUTOR", "OCCUPATIONS", "GENRES"};

        createBackupDirectory();

        ICsvBeanWriter csvBeanWriter = null;
        try {
            csvBeanWriter = new CsvBeanWriter(new FileWriter(DIRECTORY + fileName),
                    CsvPreference.STANDARD_PREFERENCE);

            csvBeanWriter.writeHeader(header);
            for (Movie m : bunchOfMovies) {
                csvBeanWriter.write(m, header);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        try {
            csvBeanWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportSeriesToCSV() {
        List<Series> bunchOfSeries = seriesService.findAll();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String timeStamp = dateFormat.format(new Date());
        String fileName = "Series_" + timeStamp + ".csv";
        String[] header = {"ID", "TITLE", "INFO", "EPISODES", "SERIESCATEGORY", "GENRES"};

        createBackupDirectory();

        ICsvBeanWriter csvBeanWriter = null;
        try {
            csvBeanWriter = new CsvBeanWriter(new FileWriter(DIRECTORY + fileName),
                    CsvPreference.STANDARD_PREFERENCE);

            csvBeanWriter.writeHeader(header);
            for (Series s : bunchOfSeries) {
                csvBeanWriter.write(s, header);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        try {
            csvBeanWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportEpisodesToCSV() {
        List<Episode> bunchOfEpisodes = episodeService.findAll();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String timeStamp = dateFormat.format(new Date());
        String fileName = "Episodes_" + timeStamp + ".csv";
        String[] header = {"ID", "TITLE", "SUMMARY", "DURATIONINSECONDS",
                "RELEASEDATE", "RATING", "DISTRIBUTOR", "OCCUPATIONS", "SEASON", "SERIES"};

        createBackupDirectory();

        ICsvBeanWriter csvBeanWriter = null;
        try {
            csvBeanWriter = new CsvBeanWriter(new FileWriter(DIRECTORY + fileName),
                    CsvPreference.STANDARD_PREFERENCE);

            csvBeanWriter.writeHeader(header);
            for (Episode e : bunchOfEpisodes) {
                csvBeanWriter.write(e, header);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        try {
            csvBeanWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createBackupDirectory() {
        File directory = new File(DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}
