-- Создание таблицы пользователей
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(100) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL
);

-- Создание таблицы списка желаний
CREATE TABLE wish_list (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT NOT NULL,
                           product_id BIGINT NOT NULL,
                           FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Создание таблицы отзывов
CREATE TABLE reviews (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         product_id BIGINT NOT NULL,
                         comment VARCHAR(255),
                         rating INT NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES users(id)
);