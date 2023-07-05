INSERT INTO Address (street, postalCode, city)
VALUES ('Kungsgatan 15', '75423', 'Uppsala'),
       ('Viavägen', '71930', 'Vintrosa'),
       ('Kyrkbacken', '69183', 'Karlskoga');

INSERT INTO Customer (userName, fullName, email, phoneNumber, addressID)
VALUES ('user1', 'John Doe', 'user1@gmail.com', '07613422', 1),
       ('JaySon', 'Jason Walker', 'jason.walk@hotmail.com', '0714253664', 2),
       ('JackBen', 'Jack Benton', 'jack_ben@gmail.com', '0797643182', 3),
       ('Sofia_Martin', 'Sofia Martinez', 'sofia.martin@xlent.se', '0700050607', 3),
       ('Salltay', 'Sally Taylor', 'sally_tay@gmail.com', '0799988813', 1);

INSERT INTO Car (rentalPricePerDay, make, model, registrationNumber, booked)
VALUES (1499, 'Volvo', 'V90', 'EDX 756', false),
       (1299, 'Volvo', 'V60', 'FGD 721', true),
       (1999, 'Volvo', 'XC90', 'GXD 542', true),
       (2000, 'Tesla', 'Model Y Performance', 'ATM 105', true),
       (1199, 'Volkswagen', 'Touran', 'FUN 369', false);

INSERT INTO Booking (bookingDate, customerID, carID, isActive)
VALUES (CURRENT_TIMESTAMP(), 1, 2, true),
(CURRENT_TIMESTAMP(), 3, 3, true),
(CURRENT_TIMESTAMP(), 3, 4, true);



