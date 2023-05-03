SET IDENTITY_INSERT [Table] ON;

--INSERT statements for Table table
INSERT INTO [Table] (tableID, occupied, capacity)
VALUES (1, 0, 4);

INSERT INTO [Table] (tableID, occupied, capacity)
VALUES (2, 1, 6);

INSERT INTO [Table] (tableID, occupied, capacity)
VALUES (3, 0, 2);

INSERT INTO [Table] (tableID, occupied, capacity)
VALUES (4, 0, 7);

INSERT INTO [Table] (tableID, occupied, capacity)
VALUES (5, 0, 8);

--INSERT statements for Customer table with Czech names
INSERT INTO [Customer] (firstName, lastName, phone, email, deletedAt)
VALUES ('Jakub', 'Novák', '+420123456789', 'jakub.novak@email.com', NULL);

INSERT INTO [Customer] (firstName, lastName, phone, email, deletedAt)
VALUES ('Tereza', 'Svobodová', '+420987654321', 'terezka@gmail.com', NULL);

INSERT INTO [Customer] (firstName, lastName, phone, email, deletedAt)
VALUES ('Pavel', 'Bartoš', '+420777888999', 'pavel.bartos@seznam.cz', '2022-04-01');

INSERT INTO [Customer] (firstName, lastName, phone, email, deletedAt)
VALUES ('Jan', 'Novotný', '+420123456789', 'jan.novotny@email.com', NULL);

INSERT INTO [Customer] (firstName, lastName, phone, email, deletedAt)
VALUES ('Lucie', 'Kovaříková', '+420987654321', 'lucie.kovarikova@gmail.com', NULL);

INSERT INTO [Customer] (firstName, lastName, phone, email, deletedAt)
VALUES ('Jiří', 'Černý', '+420777888999', 'jiri.cerny@seznam.cz', '2022-03-15');

INSERT INTO [Customer] (firstName, lastName, phone, email, deletedAt)
VALUES ('Kateřina', 'Veselá', '+420333444555', 'katerina.vesela@outlook.com', NULL);

INSERT INTO [Customer] (firstName, lastName, phone, email, deletedAt)
VALUES ('Petr', 'Novák', '+420111222333', 'petr.novak@hotmail.com', '2022-05-01');

INSERT INTO [Customer] (firstName, lastName, phone, email, deletedAt)
VALUES ('Marie', 'Horáková', '+420555666777', 'marie.horakova@yahoo.com', NULL);

--INSERT statements for Reservation table
INSERT INTO [Reservation] (customerID, tableID, start, [end], amount, created, canceled, description)
VALUES (1, 1, '2023-05-10 12:00:00', '2023-05-11 14:00:00', 50, '2023-05-03 10:00:00', NULL, 'Business lunch meeting');

INSERT INTO [Reservation] (customerID, tableID, start, [end], amount, created, canceled, description)
VALUES (2, 2, '2023-05-15 18:00:00', '2023-05-16 22:00:00', 30, '2023-05-03 11:00:00', NULL, 'Birthday party for 6 people');

INSERT INTO [Reservation] (customerID, tableID, start, [end], amount, created, canceled, description)
VALUES (1, 3, '2023-05-20 16:00:00', '2023-05-21 18:00:00', 6, '2023-05-03 13:00:00', 1, 'Canceled reservation due to illness');

INSERT INTO [Reservation] (customerID, tableID, start, [end], amount, created, canceled, description)
VALUES (3, 1, '2023-05-07 19:00:00', '2023-05-08 21:00:00', 2, '2023-05-03 14:00:00', NULL, 'Dinner for two');

INSERT INTO [Reservation] (customerID, tableID, start, [end], amount, created, canceled, description)
VALUES (4, 3, '2023-05-12 13:00:00', '2023-05-13 15:00:00', 8, '2023-05-03 15:00:00', NULL, 'Lunch meeting with client');

INSERT INTO [Reservation] (customerID, tableID, start, [end], amount, created, canceled, description)
VALUES (6, 2, '2023-05-18 20:00:00', '2023-05-19 22:00:00', 4, '2023-05-03 16:00:00', NULL, 'Birthday dinner for 4 people');

INSERT INTO [Reservation] (customerID, tableID, start, [end], amount, created, canceled, description)
VALUES (1, 1, '2023-05-22 12:00:00', '2023-05-23 14:00:00', 6, '2023-05-03 17:00:00', NULL, 'Family lunch for 6 people');

INSERT INTO [Reservation] (customerID, tableID, start, [end], amount, created, canceled, description)
VALUES (5, 3, '2023-05-28 13:00:00', '2023-05-29 15:00:00', 8, '2023-05-03 18:00:00', 1, 'Canceled reservation due to change of plans');

INSERT INTO [Reservation] (customerID, tableID, start, [end], amount, created, canceled, description)
VALUES (3, 4, GETDATE(), DATEADD(HOUR, 1, GETDATE()), 2, GETDATE(), NULL, 'Quick coffee meeting');

INSERT INTO [Reservation] (customerID, tableID, start, [end], amount, created, canceled, description)
VALUES (5, 5, DATEADD(HOUR, 1, GETDATE()), DATEADD(HOUR, 2, GETDATE()), 2, GETDATE(), NULL, 'Just a quick drink');
