CREATE TABLE IF NOT EXISTS car (
  id SERIAL PRIMARY KEY,
  year  INT NOT NULL,
  license_plate  varchar(7) UNIQUE NOT NULL,
  model varchar(100) NOT NULL,
  color  varchar(20) NOT NULL,
  user_id INT NOT NULL,
  FOREIGN KEY(user_id) REFERENCES users

);
