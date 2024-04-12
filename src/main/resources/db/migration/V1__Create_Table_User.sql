CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  first_name  varchar(20) NOT NULL,
  last_name  varchar(20) NOT NULL,
  email varchar(150) UNIQUE NOT NULL,
  birthday TIMESTAMP NULL,
  login  varchar(20) UNIQUE NOT NULL,
  password varchar(250) NOT NULL,
  phone varchar(14) NOT NULL,
  date_creation TIMESTAMP NOT NULL,
  date_last_login TIMESTAMP NOT NULL


);