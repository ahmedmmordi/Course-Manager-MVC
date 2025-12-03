INSERT INTO categories (category_name) VALUES
('Programming'),
('Web Development'),
('Databases'),
('Machine Learning'),
('DevOps');

INSERT INTO courses (course_title) VALUES
('Java Programming Basics'),
('Advanced Spring Boot'),
('Data Structures in Java'),
('Web Development with React'),
('Machine Learning Fundamentals'),
('Database Design Essentials');

INSERT INTO lessons (lesson_content, lesson_difficulty_level, lesson_duration, is_published, lesson_title, category_id, course_id) VALUES
('Basics of Java language, setup, and syntax.', 'Beginner', 35, 1, 'Introduction to Java', 1, 1),
('Classes, objects, inheritance, polymorphism.', 'Intermediate', 120, 0, 'OOP Concepts in Java', 1, 1),
('Deep dive into Java generics and collections framework.', 'Advanced', 150, 1, 'Generics and Collections', 1, 1),
('How to set up Spring Boot project.', 'Beginner', 50, 1, 'Spring Boot Setup', 1, 2),
('Building RESTful APIs with Spring Boot.', 'Intermediate', 95, 1, 'REST API Development', 2, 2),
('Implementing singly and doubly linked lists.', 'Intermediate', 75, 0, 'Linked Lists in Java', 1, 3),
('Implementing BFS, DFS, Dijkstra, and A* in Java.', 'Advanced', 160, 1, 'Graph Algorithms', 1, 3),
('Introduction to React components and JSX.', 'Beginner', 40, 1, 'React Components Basics', 2, 4),
('Using useState and useEffect hooks.', 'Intermediate', 150, 1, 'React State Management', 2, 4),
('Advanced state management using Redux.', 'Advanced', 180, 0, 'Redux Advanced Patterns', 2, 4),
('Implementing linear regression in Python.', 'Beginner', 80, 0, 'Linear Regression', 4, 5),
('Deep dive into neural networks and backpropagation.', 'Advanced', 140, 1, 'Neural Networks', 4, 5),
('Inner join, left join, right join, full outer join.', 'Intermediate', 65, 1, 'SQL Joins Explained', 3, 6);

INSERT INTO lesson_contents (content_created_at, content_document_url, content_updated_at, content_video_url, lesson_id) VALUES
('2023-05-10 14:35:00', 'https://docs.example.com/java_intro.pdf', '2023-06-01 10:20:00', 'https://videos.example.com/java_intro.mp4', 1),
('2022-12-15 09:50:00', 'https://docs.example.com/java_oop.pdf', '2023-01-05 12:15:00', 'https://videos.example.com/java_oop.mp4', 2),
('2021-08-22 11:20:00', 'https://docs.example.com/java_generics.pdf', '2021-09-05 13:45:00', 'https://videos.example.com/java_generics.mp4', 3),
('2025-06-20 11:40:00', 'https://docs.example.com/spring_setup.pdf', '2025-07-10 08:10:00', 'https://videos.example.com/spring_setup.mp4', 4),
('2021-11-03 16:25:00', 'https://docs.example.com/rest_api.pdf', '2022-12-01 14:00:00', 'https://videos.example.com/rest_api.mp4', 5),
('2020-05-15 13:30:00', 'https://docs.example.com/linked_lists.pdf', '2020-05-25 17:45:00', 'https://videos.example.com/linked_lists.mp4', 6),
('2022-02-10 09:00:00', 'https://docs.example.com/graph_algorithms.pdf', '2022-02-20 10:15:00', 'https://videos.example.com/graph_algorithms.mp4', 7),
('2025-01-20 10:00:00', 'https://docs.example.com/react_components.pdf', '2025-02-02 09:20:00', 'https://videos.example.com/react_components.mp4', 8),
('2022-10-12 08:15:00', 'https://docs.example.com/react_state.pdf', '2022-11-01 12:35:00', 'https://videos.example.com/react_state.mp4', 9),
('2020-12-05 15:00:00', 'https://docs.example.com/redux_advanced.pdf', '2021-01-10 11:30:00', 'https://videos.example.com/redux_advanced.mp4', 10),
('2024-03-05 15:50:00', 'https://docs.example.com/linear_regression.pdf', '2024-03-15 16:40:00', 'https://videos.example.com/linear_regression.mp4', 11),
('2023-07-18 09:30:00', 'https://docs.example.com/neural_networks.pdf', '2023-08-01 10:20:00', 'https://videos.example.com/neural_networks.mp4', 12),
('2021-07-01 09:30:00', 'https://docs.example.com/sql_joins.pdf', '2021-07-20 14:10:00', 'https://videos.example.com/sql_joins.mp4', 13);
