CREATE TABLE IF NOT EXISTS products (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        category VARCHAR(255) NOT NULL,
                                        price DOUBLE NOT NULL,
                                        quantity INT NOT NULL,
                                        reserved INT NOT NULL
);
