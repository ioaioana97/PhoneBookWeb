create database phone_book;
\c phone_book
create table contacts(id serial primary key not null, name varchar(45), surname varchar(45), phoneNumber varchar(11), email varchar(45));
insert into contacts(name, surname, phoneNumber, email) values('Ion', 'Popescu', '0727123456', 'ion.popescu@gmail.com');
insert into contacts(name, surname, phoneNumber, email) values('George', 'Georgescu', '0727654321', 'george.georgescu@gmail.com');
insert into contacts(name, surname, phoneNumber, email) values('Ana', 'Ionescu', '0727111111', 'ana.ionescu@gmail.com');