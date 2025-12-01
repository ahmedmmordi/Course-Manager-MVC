package org.example.coursemanager.statistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "statistics_view")
@Immutable
@Getter
@NoArgsConstructor
public class StatisticsView {
    @Id
    private Long id;

    private Long category_count;
    private Long course_count;
    private Long lesson_count;
    private Long content_count;
}
