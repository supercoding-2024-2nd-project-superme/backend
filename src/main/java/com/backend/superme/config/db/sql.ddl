
-- users 테이블 생성
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       username VARCHAR(255) NOT NULL,
                       nickname VARCHAR(255),
                       profile VARCHAR(255),
                       address VARCHAR(255),
                       gender ENUM('male', 'female', 'other'),
                       kakao_login VARCHAR(255),
                       naver_login VARCHAR(255),
                       signup_date DATETIME,
                       role ENUM('admin', 'user')
);

-- items 테이블 생성
CREATE TABLE items (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       item_name VARCHAR(255) NOT NULL,
                       main_img VARCHAR(255),
                       description TEXT NOT NULL,
                       color_option VARCHAR(255),
                       size_option VARCHAR(255),
                       stock_status ENUM('in_stock', 'out_of_stock', 'back_order'),
                       price DECIMAL(10, 2),
                       seller INT,
                       category VARCHAR(255),
                       registration_date DATETIME,
                       termination_date DATETIME,
                       FOREIGN KEY (seller) REFERENCES users(id),
                       FOREIGN KEY (category) REFERENCES categories(id)
);

-- item_stock 테이블 생성
CREATE TABLE item_stock (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            item_id INT,
                            color VARCHAR(255),
                            size VARCHAR(255),
                            stock_qty INT,
                            FOREIGN KEY (item_id) REFERENCES items(id)
);

-- categories 테이블 생성
CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255),
                            parent_category_id INT NULL,
                            INDEX idx_categories_parent_category_id (parent_category_id)
);

-- orders 테이블 생성
CREATE TABLE orders (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        user_id INT UNIQUE,
                        order_item INT,
                        total_price DECIMAL(10, 2),
                        order_date DATETIME,
                        status VARCHAR(255),
                        FOREIGN KEY (user_id) REFERENCES users(id),
                        FOREIGN KEY (order_item) REFERENCES items(id)
);
