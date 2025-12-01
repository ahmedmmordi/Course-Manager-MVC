package org.example.coursemanager.statistics.repository;

import org.example.coursemanager.statistics.model.StatisticsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsViewRepository extends JpaRepository<StatisticsView, Long> {

}
