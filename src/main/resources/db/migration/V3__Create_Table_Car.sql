CREATE TABLE IF NOT EXISTS `car` (
  `id` INT auto_increment PRIMARY KEY,
  `year`  INT NOT NULL,
  `license_plate`  varchar(7) NOT NULL,
  `model` varchar(100) NOT NULL,
  `color`  varchar(20) NOT NULL,
  `user_id` INT NOT NULL,
  FOREIGN KEY(`user_id`) REFERENCES `users`(`id`)

);
