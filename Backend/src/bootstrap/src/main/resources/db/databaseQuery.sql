CREATE DATABASE IF NOT EXISTS Ecommerce;
USE Ecommerce;

CREATE TABLE clients(
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30),
    first_surname VARCHAR(30) NOT NULL,
    last_surname VARCHAR(30),
    username VARCHAR(40) NOT NULL,
    password TEXT NOT NULL,
    is_active BOOLEAN,
    last_login DATETIME,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE shoping_carts(
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    owner_id INT,
    CONSTRAINT fk_cart_client 
        FOREIGN KEY (owner_id) REFERENCES clients(id)
        ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE categories(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)
)ENGINE=InnoDB

CREATE TABLE products(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60),
    description TEXT,
    price INT
) ENGINE=InnoDB;

CREATE TABLE orders(
    id INT AUTO_INCREMENT PRIMARY KEY,
    owner_id INT,
    CONSTRAINT fk_order_client 
        FOREIGN KEY (owner_id) REFERENCES clients(id)
        ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE products_x_categories(
    product_id INT,
    category_id INT,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT fk_productsxcategories_product
        FOREIGN KEY (product_id) REFERENCES products(id)
        ON UPDATE CASCADE,
    CONSTRAINT fk_productsxcategories_category
        FOREIGN KEY (category_id) REFERENCES categories (id)
        ON UPDATE CASCADE
)ENGINE =InnoDB

CREATE TABLE order_detail(
    order_id INT,
    product_id INT,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_orderdetail_order 
        FOREIGN KEY (order_id) REFERENCES orders(id)
        ON UPDATE CASCADE,
    CONSTRAINT fk_orderdetail_product 
        FOREIGN KEY (product_id) REFERENCES products(id)
        ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE shoping_carts_detail(
    shoping_cart_id INT,
    product_id INT,
    PRIMARY KEY (shoping_cart_id, product_id),
    CONSTRAINT fk_cartdetail_cart 
        FOREIGN KEY (shoping_cart_id) REFERENCES shoping_carts(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_cartdetail_product 
        FOREIGN KEY (product_id) REFERENCES products(id)
        ON DELETE CASCADE
) ENGINE=InnoDB;