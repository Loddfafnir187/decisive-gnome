CREATE TABLE IF NOT EXISTS carts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS positions (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         cart_id BIGINT NOT NULL,
                                         product_id BIGINT NOT NULL,
                                         quantity INT NOT NULL,
                                         FOREIGN KEY (cart_id) REFERENCES carts(id)
);
