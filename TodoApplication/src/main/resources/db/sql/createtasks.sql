CREATE TABLE tasks (
  id          INT AUTO_INCREMENT,
  username     VARCHAR(25) NOT NULL,
  heading     VARCHAR(100),
  description VARCHAR(200),
  time        DATE,
  PRIMARY KEY (id),
  FOREIGN KEY username(usrname) REFERENCES users(username) on DELETE CASCADE
)