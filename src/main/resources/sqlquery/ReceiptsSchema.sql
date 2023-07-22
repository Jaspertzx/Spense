drop table [dbo].[Receipts]

CREATE TABLE Receipts (
  receiptID INT IDENTITY(1,1) PRIMARY KEY,
  dateissued INT,
  items VARCHAR(MAX),
  price FLOAT,
  discount FLOAT,
  paymentmethod VARCHAR(2048),
  business INT,
  staffname VARCHAR(512),
  warranty INT
  category varchar(100),
);

ALTER TABLE Receipts
ADD UserID INT;

ALTER TABLE Receipts
ADD CONSTRAINT FK_UserID
FOREIGN KEY (UserID) REFERENCES Users(id);