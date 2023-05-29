use holidayCenter;

--values(hasBalcony, floorNo, noOfBeds, viewDescription)
--values(apartmentNo, apartmentType, apartmentDescription_FK)
insert into ApartmentDescription values (1, 2, 2, 'Trashcans');
insert into Apartment values ('1', 'Single', 1);
insert into ApartmentDescription values (1, 2, 2, 'Trashcans2');
insert into Apartment values ('2', 'Single', 2);
insert into ApartmentDescription values (1, 2, 2, 'Trashcans3');
insert into Apartment values ('3', 'Single', 3);
insert into ApartmentDescription values (0, 1, 3, 'Swimming Pool');
insert into Apartment values ('4', 'Family', 4);
insert into ApartmentDescription values (0, 1, 5, 'The Ocean');
insert into Apartment values ('5', 'Penthouse', 5);
insert into ApartmentDescription values (1, 3, 4, 'Aids');
insert into Apartment values ('6', 'Single', 6);
insert into ApartmentDescription values (1, 1, 3, 'Cancer');
insert into Apartment values ('7', 'Family', 7);
insert into ApartmentDescription values (0, 2, 2, 'Pest');
insert into Apartment values ('8', 'Single', 8);
insert into ApartmentDescription values (1, 3, 4, 'HIV');
insert into Apartment values ('9', 'Penthouse', 9);
insert into ApartmentDescription values (1, 1, 1, 'Kasper');
insert into Apartment values ('10', 'Family', 10);


insert into ZipCity values (9000, 'Aalborg');
insert into Address values ('Boulevarden', 31, 9000);

insert into ZipCity values (8930, 'Randers');
insert into Address values ('Lolgade', 69, 8930);

insert into Person values ('Smaji', 'Omanovic', 1, '123541236', 'smaji@', '1111', '1111', '1', 'g');
insert into Person values ('Kasper', 'Christiansen', 2, '9394267348', 'Kasp@', '2222', '2222', '2', 'e');

insert into Guest values (25, 'Denenmark', 'smaji@');
insert into Employee values (25, 'Sales', 'Kasp@');

insert into ZipCity values ('zip', 'city');
insert into Address values ('street', 'house', 'zip')
insert into Person values ('Joe', 'Biden', 3, '12345678', 'joeb@', '123', '123', '1', 'e');
insert into Employee values ('23', 'Receptionist', 'joeb@');


insert into Booking values (10387, 'none', '2023-12-05', 7, 0, 0, 0, 25, 25, 10000);

insert into ApartmentBooking values (1, 1);

insert into Price values (1, GETDATE(), 500);
insert into Price values (2, GETDATE(), 700);
insert into Price values (3, GETDATE(), 900);