CREATE SCHEMA new_schema
	AUTHORIZATION sa;
CREATE TABLE IF NOT EXISTS new_schema.authors
(
	id   int(11)      NOT NULL AUTO_INCREMENT,
	name varchar(110) NOT NULL,
	dob  date,
	rank int(4)                DEFAULT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS new_schema.genres
(
	id   int(11)     NOT NULL AUTO_INCREMENT,
	name varchar(60) NOT NULL,
	PRIMARY KEY (id)
);

create table if not exists new_schema.books
(
	book_id         int(11)      NOT NULL AUTO_INCREMENT,
	title           varchar(100) not null,
	pages           integer,
	count           integer,
	author          integer
		constraint books_authors_id_fk
		references authors,
	genre           integer
		constraint books_genres_id_fk
		references genres,
	year_publishing date,
	PRIMARY KEY (book_id)
);

create table if not exists new_schema.comments
(
  id        integer not null
    constraint comments_pkey
    primary key,
  comment   varchar(200),
  rating    integer,
  book_id   integer not null,
  reader_id integer not null
);

create table if not exists new_schema.readers
(
	name      varchar(120) not null,
	rank      integer,
	reader_id int8         not null
		constraint readers_reader_id_pk
		primary key,
	discount  smallint
);

create table if not exists new_schema.borrows
(
	reader_id   integer not null
		constraint borrows_readers_reader_id_fk
		references readers,
	book_id     integer not null
		constraint borrows_books_book_id_fk
		references books,
	date_from   date    not null,
	date_till   date,
	date_return date,
	constraint borrows_pk
	primary key (reader_id, book_id, date_from)
);

create sequence authors_id_seq;
create sequence genres_id_seq;
create sequence books_book_id_seq;
create sequence readers_reader_id_seq;


