DROP TABLE [User];

CREATE TABLE [User] (
    id                          int identity constraint User_pk primary key,
    login                       varchar(7),
    name                        varchar(30),
    surname                     varchar(30),
    address                     varchar(100),
    telephone                   varchar(20),
    maximum_unfinished_auctions int,
    last_visit                  datetime,
    type                        varchar(10)
)

INSERT INTO [User] (login, name, surname, address, telephone, maximum_unfinished_auctions, last_visit, type)
VALUES ('nov0124', 'Petr', 'Novák', 'Ulice Příkladu 123, Praha 1, 110 00', '+420 123 456 789', 3, '2022-05-02 11:00:00', 'buyer');

INSERT INTO [User] (login, name, surname, address, telephone, maximum_unfinished_auctions, last_visit, type)
VALUES ('nov1456', 'Jan', 'Novotný', 'Na Pankráci 123/5, Praha 4, 140 00', '+420 111 222 333', 7, '2022-05-02 12:30:00', 'buyer');

INSERT INTO [User] (login, name, surname, address, telephone, maximum_unfinished_auctions, last_visit, type)
VALUES ('svo0789', 'Marie', 'Svobodová', 'Náměstí Míru 456/7, Brno, 602 00', '+420 222 333 444', 2, '2022-05-01 18:15:00', 'seller');

INSERT INTO [User] (login, name, surname, address, telephone, maximum_unfinished_auctions, last_visit, type)
VALUES ('nov0123', 'Tomáš', 'Novák', 'Lidická 789/10, Ostrava, 702 00', '+420 333 444 555', 5, '2022-04-30 09:45:00', 'buyer');

INSERT INTO [User] (login, name, surname, address, telephone, maximum_unfinished_auctions, last_visit, type)
VALUES ('kov5456', 'Petra', 'Kovářová', 'Příčná 234/1, Plzeň, 301 00', '+420 444 555 666', 10, '2022-04-29 14:20:00', 'seller');
