DROP DATABASE IF EXISTS greenhousedatabase;

CREATE DATABASE greenhousedatabase;

USE greenhousedatabase;

create table user(
	user_id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	username VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(500) NOT NULL,
	email VARCHAR(50) NOT NULL,
	PRIMARY KEY(user_id)
);

create table greenhouse(
	greenhouse_id varchar(50) NOT NULL UNIQUE,
	password VARCHAR(500) NOT NULL,
	ip varchar (15),
	portnumber varchar (20),
	PRIMARY KEY(greenhouse_id)
	);
	
create table datalog(
	greenhouse_id varchar(50) not null,
	time_of_reading timestamp not null,
	internal_temperature float,
	external_temperature float,
	humidity float,
	water_level float,
	PRIMARY KEY(greenhouse_id, time_of_reading)
);

create table schedule(
	schedule_id varchar(50) not null,
	blocknumber smallint unsignt not null,
	temperature float,
	humidity float, 
	waterlevel float,
	red float,
	blue float,
	primary key (schedule_id, blocknumber)
	);
	
	--check lige op på hvilke datatyper der skal bruges
	--double tænker jeg vi caster til float idet jeg ikke tror på at vi rammer 
	--målinger hvor tallet er stort nok til at float ikke kan dække behovet.
	--Hvis der nogen int værdier skal schedule table lige ændres til at have de rigtige værdier.
	
create table greenhouse_user(
	user_id integer unsigned not null,
	greenhouse_id integer unsigned not null
);

create table greenhouse_shcedule(
	greenhouse_id integer unsigned not null,
	schedule_id varchar(50) not null,
	startdate date,
	primary key (greenhouse_id, schedule_id)
);


--INSERT INTO user(user_id, username, password, email) values (1, admin, <HASHED PASSWORD HERE>, admin@admin.pikfjæs);