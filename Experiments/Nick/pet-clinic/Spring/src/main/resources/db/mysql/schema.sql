CREATE TABLE IF NOT EXISTS owners (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  address VARCHAR(255),
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  telephone VARCHAR(20),
  INDEX(last_name)
) engine=InnoDB;
