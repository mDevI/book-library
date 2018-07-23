CREATE SCHEMA new_schema
	AUTHORIZATION sa;
CREATE TABLE IF NOT EXISTS new_schema.authors
(
	id   int(11)      NOT NULL AUTO_INCREMENT,
	name varchar(110) NOT NULL,
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
	book_id         integer      not null
		constraint books_pkey
		primary key,
	title           varchar(100) not null,
	pages           integer,
	count           integer,
	author          integer
		constraint books_authors_id_fk
		references authors,
	genre           integer
		constraint books_genres_id_fk
		references genres,
	year_publishing date         not null
);

create sequence authors_id_seq;
create sequence genres_id_seq;
create sequence books_book_id_seq;

