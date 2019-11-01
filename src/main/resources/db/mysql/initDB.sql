CREATE DATABASE IF NOT EXISTS library;

ALTER DATABASE library
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON library.* TO pc@localhost IDENTIFIED BY 'pc';

USE library;

CREATE TABLE IF NOT EXISTS users (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_name VARCHAR(30),
  password VARCHAR(80),
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  address VARCHAR(255),
  role VARCHAR(30),
  status VARCHAR(30),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS books (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(30),
  author VARCHAR(30),
  status VARCHAR(30),
  type VARCHAR(30)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS borrow_history (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id INT(4) UNSIGNED NOT NULL,
  book_id INT(4) UNSIGNED NOT NULL,
  date_borrow DATE,
  date_turnback DATE
 
) engine=InnoDB;