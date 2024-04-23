CREATE TABLE IF NOT EXISTS `users` (
  `id` INT auto_increment PRIMARY KEY,
  `first_name`  varchar(20) NOT NULL,
  `last_name`  varchar(20) NOT NULL,
  `email` varchar(150) NOT NULL,
  `birthday` datetime(6) NULL,
  `user_name`  varchar(20) NOT NULL,
  `password` varchar(250) NOT NULL,
  `phone` varchar(14) NOT NULL,
  `date_creation` datetime(6) NOT NULL,
  `date_last_login` datetime(6) NOT NULL,
  `account_non_expired` boolean DEFAULT TRUE,
  `account_non_locked` boolean DEFAULT TRUE,
  `credentials_non_expired` boolean DEFAULT TRUE,
  `enabled` boolean DEFAULT TRUE


);