CREATE VIEW statistics_view AS
    SELECT 1 AS id,
        (SELECT COUNT(*) FROM categories) AS category_count,
        (SELECT COUNT(*) FROM courses) AS course_count,
        (SELECT COUNT(*) FROM lessons) AS lesson_count,
        (SELECT COUNT(*) FROM lesson_contents) AS content_count;
