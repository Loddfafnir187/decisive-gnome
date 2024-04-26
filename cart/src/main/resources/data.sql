-- Добавление примеров корзин
INSERT INTO carts VALUES (1);
INSERT INTO carts VALUES (2);
INSERT INTO carts VALUES (3);

-- Добавление примеров позиций в корзине №1
INSERT INTO positions (cart_id, product_id, quantity) VALUES (1, 1, 2);
INSERT INTO positions (cart_id, product_id, quantity) VALUES (1, 2, 1);
INSERT INTO positions (cart_id, product_id, quantity) VALUES (1, 3, 3);

-- Добавление примеров позиций в корзине №2
INSERT INTO positions (cart_id, product_id, quantity) VALUES (2, 4, 1);
INSERT INTO positions (cart_id, product_id, quantity) VALUES (2, 5, 2);

-- Добавление примеров позиций в корзине №3
INSERT INTO positions (cart_id, product_id, quantity) VALUES (3, 6, 3);
