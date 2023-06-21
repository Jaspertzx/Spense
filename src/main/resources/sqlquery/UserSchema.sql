CREATE TABLE Users
(
id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
username varchar(50),
password varchar(512),
email varchar(255),
mobilePhone int,
date int,
salt varchar(512),
);
