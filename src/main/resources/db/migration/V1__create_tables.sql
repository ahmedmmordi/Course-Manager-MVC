CREATE TABLE categories (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(30) NOT NULL
);

CREATE TABLE courses (
    course_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_title VARCHAR(50) NOT NULL
);

CREATE TABLE lessons (
    lesson_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lesson_title VARCHAR(120) NOT NULL,
    lesson_duration INT,
    lesson_difficulty_level VARCHAR(20),
    lesson_content VARCHAR(1000),
    is_published BOOLEAN NOT NULL DEFAULT FALSE,
    course_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    CONSTRAINT fk_lesson_course FOREIGN KEY (course_id) REFERENCES courses(course_id),
    CONSTRAINT fk_lesson_category FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE lesson_contents (
    content_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content_video_url VARCHAR(150) NOT NULL,
    content_document_url VARCHAR(150),
    lesson_id BIGINT NOT NULL UNIQUE,
    content_created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    content_updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_content_lesson FOREIGN KEY (lesson_id) REFERENCES lessons(lesson_id) ON DELETE CASCADE
);
