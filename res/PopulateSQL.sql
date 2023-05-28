
insert into ApartmentDescription values (1, 2, 2, 'Trashcans');
insert into ApartmentDescription values (0, 1, 3, 'Swimming Pool');
insert into ApartmentDescription values (0, 1, 5, 'The Ocean');

insert into Apartment values ('1', 'Single', 1);
insert into Apartment values ('2', 'Family', 2);
insert into Apartment values ('3', 'Penthouse', 3);


insert into ZipCity values (9000, 'Aalborg');
insert into Address values ('Boulevarden', 31, 9000);

insert into ZipCity values (8930, 'Randers');
insert into Address values ('Lolgade', 69, 8930);

insert into Person values ('Smaji', 'Omanovic', 1, '12345678', 'smaji@', '1111', '1111', '1', 'g');
insert into Person values ('Kasper', 'Christiansen', 1, '9394267348', 'Kasp@', '2222', '2222', '2', 'e');

insert into Guest values (25, 'Denenmark', 'smaji@');
insert into Employee values (25, 'Sales', 'Kasp@');


insert into Booking values (10387, 'none', GETDATE(), 7, 0, 0, 0, 25, 25, 10000);

insert into ApartmentBooking values (1, 1);

insert into Price values (1, GETDATE(), 500);
insert into Price values (2, GETDATE(), 700);
insert into Price values (3, GETDATE(), 900);