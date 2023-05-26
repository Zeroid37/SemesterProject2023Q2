-- drop if necessary, then create "company"
use master;
if exists (select * from sys.databases where name='holidayCenter')
	drop database holidayCenter;
go

create database holidayCenter;
go

use holidayCenter;

-- create tables

create table ZipCity( 
	zip varchar(16) not null,
	city varchar(20) not null,
	primary key (zip)
);

create table Address(
	id varchar(12) unique not null,
	zip_FK varchar(16) not null,
	street varchar(16) not null,
	houseNo varchar(10) not null,
	primary key (id),
	foreign key (zip_FK) references ZipCity(zip)
);

create table Person(
	id varchar(12) unique not null,
	fristName varchar(12) not null,
	lastName varchar(20) not null,
	addressId_FK varchar(12) not null,
	phone varchar(16) unique not null,
	email varchar(30) unique not null,
	userID varchar(16) unique not null,
	password varchar(16) not null,
	isAdmin bit,
	type int not null,
	primary key (email),
	foreign key (addressId_FK) references Address(id)
);

create table Instructor(
	instructorNo varchar(16) unique not null,
	email_FK varchar(30) unique not null,
	primary key (instructorNo),
	foreign key (email_FK) references Person(email)
);

create table Employee(
	employeeNo int unique not null,
	email_FK varchar(30) unique not null,
	department varchar(16) not null,
	primary key (employeeNo),
	foreign key (email_FK) references Person(email)
);

create table Guest(
	guestNo int unique not null,
	country varchar(70) not null,
	email_FK varchar(30) unique not null,
	primary key (guestNo),
	foreign key (email_FK) references Person(email)
);

create table Profession(
	id varchar(12) unique not null,
	name varchar(16) not null,
	hourlyPrice money not null,
	level varchar(16) not null,
	primary key (id)
);

create table InstructorProfession(
	proessionID varchar(12) not null,
	instructorNo varchar(16) not null,
	primary key (proessionID, instructorNo),
	foreign key (proessionID) references Profession(id),
	foreign key (instructorNo) references Instructor(instructorNo)
);

create table Facility(
	facilityNo int unique not null,
	facilityType varchar(20) not null,
	capacity int not null,
	primary key (facilityNo)
);

create table ApartmentDescription(
	id varchar(12) unique not null,
	hasBalcony bit not null,
	floorNo int not null,
	numberOfKingBeds int,
	numberOfQueenBeds int,
	numberOfNormalBeds int,
	viewDescription varchar(16) not null,
	primary key (id),
);

create table Apartment(
	apartmentNo int unique not null,
	apartmentType varchar(16) not null,
	apartmentDescription_FK varchar(12) not null,
	primary key (apartmentNo),
	foreign key (apartmentDescription_FK) references ApartmentDescription(id),
);

create table Booking(
	id varchar(12) unique not null,
	bookingNo int unique not null,
	travelAgency varchar,
	checkinDate datetime not null,
	noOfNights int not null,
	discount int,
	isDepositPaid bit,
	activityQuantityToday int,
	employeeNo_FK int not null,
	guestNo_FK int not null,
	apartmentNo_FK int not null,
	price money not null,
	primary key (id),
	foreign key (guestNo_FK) references Guest (guestNo),
	foreign key (employeeNo_FK) references Employee(employeeNo),
	foreign key (apartmentNo_FK) references Apartment(apartmentNo)
);

create table ApartmentBooking(
	apartmentNo int not null,
	bookingId varchar(12) not null,
	primary key (apartmentNo, bookingId),
	foreign key (apartmentNo) references Apartment(apartmentNo),
	foreign key (bookingId) references booking(id)
);

create table Reservation(
	id varchar(12) unique not null,
	startDate datetime not null,
	duration int not null,
	attendeeAmount int not null,
	professionID_FK varchar(12) not null,
	facilityNo_FK int not null,
	instructorNo_FK varchar(16) not null,
	bookingID_FK varchar(12) not null,
	primary key (id),
	foreign key (professionID_FK) references Profession(id),
	foreign key (facilityNo_FK) references Facility(facilityNo),
	foreign key (instructorNo_FK) references Instructor(instructorNo),
	foreign key (bookingID_FK) references Booking(id)
);

create table Invoice(
	id varchar(12) unique not null,
	invoiceNo int unique not null,
	datePaid datetime not null,
	sum int not null,
	bookingID_FK varchar(12) not null,
	primary key (id),
	foreign key (bookingID_FK) references Booking(id)
);

create table Price(
	id varchar(12) unique not null,
	apartmentDescription_FK varchar(12) not null,
	dateFrom datetime not null,
	price money not null,
	primary key (id),
	foreign key (apartmentDescription_FK) references ApartmentDescription(id)
);