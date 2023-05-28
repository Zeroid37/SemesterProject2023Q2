/*
select * from Apartment
select * from Guest, Employee

select * from Booking

select * from ApartmentBooking
*/
--select bookingId from ApartmentBooking where apartmentNo = 1;

/*
select * from ApartmentDescription
INNER JOIN Apartment ON Apartment.apartmentDescription_FK = ApartmentDescription.id
INNER JOIN Price ON price.apartmentDescription_FK = ApartmentDescription.id
*/

/*
select Apartment.apartmentNo, Apartment.apartmentType, numberOfBeds, floorNo, hasBalcony, price from ApartmentDescription
INNER JOIN Apartment ON Apartment.apartmentDescription_FK = ApartmentDescription.id
INNER JOIN Price ON price.apartmentDescription_FK = ApartmentDescription.id
--where apartmentType = 'Single' and numberOfBeds = 2 and floorNo = 2 and hasBalcony = 1 and price between 500 and 600
*/

select Apartment.apartmentNo, Apartment.apartmentType, numberOfBeds, floorNo, hasBalcony, price from ApartmentDescription
INNER JOIN Apartment ON Apartment.apartmentDescription_FK = ApartmentDescription.id
INNER JOIN Price ON price.apartmentDescription_FK = ApartmentDescription.id
where apartmentType = 'Single'
