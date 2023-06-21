CREATE TABLE Outlets (
outletsID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
address varchar(1024),
openingHours varchar(MAX),
field varchar(2048),
businessID int,
FOREIGN KEY (businessID) REFERENCES [dbo].[Business](businessID)
);