-- Вставка начальных данных для таблицы пользователей
INSERT INTO users (username, password, email) VALUES
                                                  ('user1', 'password1', 'user1@example.com'),
                                                  ('user2', 'password2', 'user2@example.com'),
                                                  ('user3', 'password3', 'user3@example.com');


-- Вставка начальных данных для таблицы списка желаний
INSERT INTO wish_list (user_id, product_id) VALUES
                                                (1, 1),
                                                (1, 2),
                                                (2, 1),
                                                (3, 2),
                                                (3, 1);

-- Вставка начальных данных для таблицы отзывов
INSERT INTO reviews (user_id, product_id, comment, rating) VALUES
                                                               (1, 1, 'Great product', 5),
                                                               (2, 2, 'Good product', 4);
