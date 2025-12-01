package org.example.coursemanager.statistics.service;

import org.example.coursemanager.statistics.model.StatisticsView;
import org.example.coursemanager.statistics.repository.StatisticsViewRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticsViewService {
    private final StatisticsViewRepository statisticsViewRepository;
    public StatisticsViewService(StatisticsViewRepository statisticsViewRepository) {
        this.statisticsViewRepository = statisticsViewRepository;
    }

    public StatisticsView getStatistics() {
        return statisticsViewRepository.findById(1L).orElseThrow(() -> new RuntimeException("Statistics Not Available."));
    }
}
