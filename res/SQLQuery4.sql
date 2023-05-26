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
	zip int not null,
	city varchar(20) not null,
	primary key (zip)
);

create table Address(
	id char(12) unique not null,
	zip_FK int not null,
	street varchar not null,
	houseNo int not null,
	primary key (id),
	foreign key (zip_FK) references ZipCity(zip)
);

create table Person(
	id char(12) unique not null,
	fristName varchar(12) not null,
	lastName varchar(20) not null,
	addressId_FK char(12) not null,
	phone int unique not null,
	email varchar unique not null,
	userID char (16) unique not null,
	password varchar not null,
	isAdmin bit,
	type int not null,
	primary key (email),
	foreign key (addressId_FK) references Address(id)
);

create table Instructor(
	instructorNo varchar unique not null,
	email_FK varchar unique not null,
	primary key (instructorNo),
	foreign key (email_FK) references Person(email)
);

create table Employee(
	employeeNo int unique not null,
	email_FK varchar unique not null,
	department varchar(16) not null,
	primary key (employeeNo),
	foreign key (email_FK) references Person(email)
);

create table Guest(
	guestNo int unique not null,
	country varchar not null,
	email_FK varchar unique not null,
	primary key (guestNo),
	foreign key (email_FK) references Person(email)
);

create table Profession(
	id char(12) unique not null,
	name varchar not null,
	hourlyPrice money not null,
	level varchar not null,
	primary key (id)
);

create table InstructorProfession(
	proessionID char(12) not null,
	instructorNo varchar not null,
	primary key (proessionID, instructorNo),
	foreign key (proessionID) references Profession(id),
	foreign key (instructorNo) references Instructor(instructorNo)
);

create table Facility(
	facilityNo int unique not null,
	facilityType varchar (20) not null,
	capacity int not null,
	primary key (facilityNo)
);

create table ApartmentDescription(
	id char(12) unique not null,
	hasBalcony bit not null,
	floorNo int not null,
	numberOfKingBeds int,
	numberOfQueenBeds int,
	numberOfNormalBeds int,
	viewDescription varchar not null,
	primary key (id),
);

create table Apartment(
	apartmentNo int unique not null,
	apartmentType varchar not null,
	apartmentDescription_FK char(12) not null,
	primary key (apartmentNo),
	foreign key (apartmentDescription_FK) references ApartmentDescription(id),
);

create table Booking(
	id char(12) unique not null,
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
	bookingId char(12) not null,
	primary key (apartmentNo, bookingId),
	foreign key (apartmentNo) references Apartment(apartmentNo),
	foreign key (bookingId) references booking(id)
);

create table Reservation(
	id char (12) unique not null,
	startDate datetime not null,
	duration int not null,
	attendeeAmount int not null,
	professionID_FK char (12) not null,
	facilityNo_FK int not null,
	instructorNo_FK  varchar not null,
	bookingID_FK char(12) not null,
	primary key (id),
	foreign key (professionID_FK) references Profession(id),
	foreign key (facilityNo_FK) references Facility(facilityNo),
	foreign key (instructorNo_FK) references Instructor(instructorNo),
	foreign key (bookingID_FK) references Booking(id)
);

create table Invoice(
	id char(12) unique not null,
	invoiceNo int unique not null,
	datePaid datetime not null,
	sum int not null,
	bookingID_FK char(12) not null,
	primary key (id),
	foreign key (bookingID_FK) references Booking(id)
);

create table Price(
	id char(12) unique not null,
	apartmentDescription_FK char (12) not null,
	dateFrom datetime not null,
	price money not null,
	primary key (id),
	foreign key (apartmentDescription_FK) references ApartmentDescription(id)
);