BEGIN TRANSACTION;

IF OBJECT_ID('Table', 'U') IS NOT NULL
    ALTER TABLE [Reservation] DROP CONSTRAINT FK_ReservationTableID
    DROP TABLE [Table];

IF OBJECT_ID('Customer', 'U') IS NOT NULL
    ALTER TABLE [Reservation] DROP CONSTRAINT FK_ReservationCustomerID
    DROP TABLE [Customer];

IF OBJECT_ID('Reservation', 'U') IS NOT NULL
    DROP TABLE [Reservation];

CREATE TABLE [Table] (
                       tableID INTEGER PRIMARY KEY IDENTITY(1,1),
                       occupied INTEGER NOT NULL,
                       capacity INTEGER NOT NULL
);

CREATE TABLE [Customer] (
                          customerID INTEGER PRIMARY KEY IDENTITY(1,1),
                          firstName VARCHAR(100) NOT NULL,
                          lastName VARCHAR(100) NOT NULL,
                          phone VARCHAR(15) NOT NULL,
                          email VARCHAR(50) NOT NULL,
                          deletedAt DATETIME
);


CREATE TABLE [Reservation] (
                             reservationID INTEGER PRIMARY KEY IDENTITY(1,1),
                             customerID INTEGER,
                             tableID INTEGER,
                             start DATETIME NOT NULL,
                             [end] DATETIME NOT NULL,
                             amount INTEGER,
                             created DATETIME NOT NULL,
                             canceled INTEGER,
                             description VARCHAR(500),
                             CONSTRAINT FK_ReservationCustomerID FOREIGN KEY (customerID) REFERENCES [Customer](customerID),
                             CONSTRAINT FK_ReservationTableID FOREIGN KEY (tableID) REFERENCES [Table](tableID)
);

COMMIT TRANSACTION;
