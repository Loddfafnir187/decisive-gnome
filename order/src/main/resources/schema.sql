CREATE TABLE IF NOT EXISTS orders (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      client_id BIGINT NOT NULL,
                                      prod_id BIGINT NOT NULL,
                                      quantity INT NOT NULL,
                                      date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      status VARCHAR(50) NOT NULL
);
