package org.example.coursemanager.statistics.controller;

import org.example.coursemanager.statistics.model.StatisticsView;
import org.example.coursemanager.statistics.service.StatisticsViewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatisticsViewController {
    private final StatisticsViewService statisticsViewService;
    public StatisticsViewController(StatisticsViewService statisticsViewService) {
        this.statisticsViewService = statisticsViewService;
    }

    @GetMapping("")
    public String showStatistics(Model model) {
        StatisticsView stats = statisticsViewService.getStatistics();
        model.addAttribute("statistics", stats);
        return "statistics/statistics";
    }
}
