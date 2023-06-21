CREATE TABLE items (
  id INT PRIMARY KEY IDENTITY(1,1),
  businessID INT NOT NULL,
  name VARCHAR(255),
  price FLOAT,
  PRIMARY KEY (businessID, id)
);