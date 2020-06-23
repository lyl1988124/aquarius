CREATE TABLE user (
  id bigint not null AUTO_INCREMENT,
  user_name varchar(32) NOT NULL,
  password varchar(50) NOT NULL,
  real_name varchar(32),
  primary key(id)
);