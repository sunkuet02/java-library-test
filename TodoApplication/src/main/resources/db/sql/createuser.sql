CREATE TABLE users (
  id       INT AUTO_INCREMENT,
  username VARCHAR(25) NOT NULL UNIQUE,
  password VARCHAR(50),
  email    VARCHAR(50),
  PRIMARY KEY (id)
);