package gr.sae.service;

import gr.sae.domain.Episode;
import gr.sae.domain.Genre;
import gr.sae.domain.Series;
import gr.sae.repository.SeriesRepository;
import gr.sae.transfer.MoviesAndSeriesPerGenreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl extends BaseServiceImpl<Series> implements SeriesService {
    private final SeriesRepository seriesRepository;
    private EpisodeService episodeService;

    @Autowired
    @Lazy
    public void setEpisodeService(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    JpaRepository<Series, Long> getRepository() {
        return seriesRepository;
    }

    @Override
    public Series get(String title) {
        return seriesRepository.getByTitle(title);
    }

    @Override
    public Series find(String title) {
        return seriesRepository.findByTitle(title);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void delete(Series series) {
        Set<Episode> episodes = new HashSet<>(series.getEpisodes());

        for (Episode e : episodes) {
            removeEpisode(series, e);
        }

        super.delete(series);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Series series = find(id);
        Set<Episode> episodes = new HashSet<>(series.getEpisodes());

        for (Episode e : episodes) {
            removeEpisode(series, e);
        }

        super.delete(series);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    public void deleteByTitle(String title) {
        Series series = find(title);
        Set<Episode> episodes = new HashSet<>(series.getEpisodes());

        for (Episode e : episodes) {
            removeEpisode(series, e);
        }

        super.delete(series);
    }

    @Override
    public List<Series> findByGenre(Genre genre) {
        return seriesRepository.findSeriesByGenresContaining(genre);
    }

    @Override
    public List<MoviesAndSeriesPerGenreDto> findSeriesPerGenre() {
        return seriesRepository.findSeriesPerGenre();
    }

    @Override
    public void addEpisode(Series series, Episode episode) {
        if (isNull(series) || isNull(episode)) {
            return;
        }

        episode.setSeries(series);
        series.addEpisode(episode);
        episodeService.create(episode);

        logger.debug("Episode[{}] added to Series[{}]", episode, series);
    }

    @Override
    public void removeEpisode(Series series, Episode episode) {
        if (isNull(series) || isNull(episode)) {
            return;
        }

        episodeService.deleteOccupations(episode);
        series.removeEpisode(episode);
        update(series);

        logger.debug("Episode[{}] removed from Series[{}]", episode, series);
    }

    @Override
    public void updateEpisode(Series series, Episode episode) {
        series.updateEpisode(episode);
        episodeService.update(episode);

        logger.debug("Episode[{}] updated in Series[{}]", episode, series);
    }

    private boolean isNull(Object object) {
        return object == null;
    }
}
