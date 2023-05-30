--values(hasBalcony, floorNo, noOfBeds, viewDescription)
--values(apartmentNo, apartmentType, apartmentDescription_FK)
insert into ApartmentDescription values (1, 2, 2, 'Trashcans');
insert into Apartment values ('1', 'Single', 1);
insert into ApartmentDescription values (1, 2, 2, 'Ocean');
insert into Apartment values ('2', 'Single', 2);
insert into ApartmentDescription values (1, 2, 2, 'Ocean');
insert into Apartment values ('3', 'Single', 3);
insert into ApartmentDescription values (0, 1, 3, 'Swimming Pool');
insert into Apartment values ('4', 'Family', 4);
insert into ApartmentDescription values (0, 1, 5, 'Beach');
insert into Apartment values ('5', 'Penthouse', 5);
insert into ApartmentDescription values (1, 3, 4, 'Back Alley');
insert into Apartment values ('6', 'Single', 6);
insert into ApartmentDescription values (1, 1, 3, 'No windows');
insert into Apartment values ('7', 'Family', 7);
insert into ApartmentDescription values (0, 2, 2, 'City');
insert into Apartment values ('8', 'Single', 8);
insert into ApartmentDescription values (1, 3, 4, 'Darkness');
insert into Apartment values ('9', 'Penthouse', 9);
insert into ApartmentDescription values (1, 1, 1, 'Kasper');
insert into Apartment values ('10', 'Family', 10);


insert into ZipCity values (9000, 'Aalborg');
insert into Address values ('Boulevarden', 15, 9000);

insert into ZipCity values (8930, 'Randers');
insert into Address values ('Glentevej', 42, 8930);

insert into Person values ('Smajo', 'Omanovic', 1, '123541236', 'Smajo@', '1111', '1111', '1', 'g');
insert into Person values ('Kasper', 'Christiansen', 2, '9394267348', 'Kasp@', '2222', '2222', '2', 'e');

insert into Guest values (25, 'Denmark', 'Smajo@');
insert into Employee values (25, 'Sales', 'Kasp@');

insert into ZipCity values ('7160', 'Tørring');
insert into Address values ('Ådalvej', '46', '7160')
insert into Person values ('Joe', 'Biden', 3, '12345678', 'JoeB@', '123', '123', '1', 'e');
insert into Employee values ('23', 'Receptionist', 'JoeB@');


insert into Booking values (10387, 'none', '2023-12-05', 7, 0, 0, 0, 25, 25, 10000);
insert into ApartmentBooking values (1, 1);

insert into Booking values (10388, 'none', '2024-06-23', 12, 0, 0, 0, 25, 25, 10000);
insert into ApartmentBooking values (1, 2);


insert into Price values (1, GETDATE(), 500);
insert into Price values (2, GETDATE(), 700);
insert into Price values (3, GETDATE(), 900);