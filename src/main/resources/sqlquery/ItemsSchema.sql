CREATE TABLE items (
  id INT PRIMARY KEY IDENTITY(1,1),
  businessID INT NOT NULL,
  name VARCHAR(255),
  price FLOAT,
  receiptId varchar(255)
);