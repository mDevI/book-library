--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.6
-- Dumped by pg_dump version 9.6.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS library;
--
-- Name: library; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE library WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';


ALTER DATABASE library OWNER TO postgres;

\connect library

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: new_schema; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA new_schema;


ALTER SCHEMA new_schema OWNER TO postgres;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;

--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = new_schema, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: authors; Type: TABLE; Schema: new_schema; Owner: postgres
--

CREATE TABLE authors (
  id   integer NOT NULL,
  name character varying(110),
  dob  date,
  rank integer DEFAULT 0
);


ALTER TABLE authors OWNER TO postgres;

--
-- Name: authors_id_seq; Type: SEQUENCE; Schema: new_schema; Owner: postgres
--

CREATE SEQUENCE authors_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE authors_id_seq OWNER TO postgres;

--
-- Name: authors_id_seq; Type: SEQUENCE OWNED BY; Schema: new_schema; Owner: postgres
--

ALTER SEQUENCE authors_id_seq OWNED BY authors.id;

--
-- Name: books; Type: TABLE; Schema: new_schema; Owner: postgres
--

CREATE TABLE books (
  book_id         integer                NOT NULL,
  title           character varying(100) NOT NULL,
  pages           integer,
  count           integer,
  author          integer,
  genre           integer,
  year_publishing date                   NOT NULL
);


ALTER TABLE books OWNER TO postgres;

--
-- Name: books_id_seq; Type: SEQUENCE; Schema: new_schema; Owner: postgres
--

CREATE SEQUENCE books_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE books_id_seq OWNER TO postgres;

--
-- Name: books_id_seq; Type: SEQUENCE OWNED BY; Schema: new_schema; Owner: postgres
--

ALTER SEQUENCE books_id_seq OWNED BY books.book_id;

--
-- Name: borrows; Type: TABLE; Schema: new_schema; Owner: postgres
--

CREATE TABLE borrows (
  borrow_id    integer NOT NULL,
  reader_id    integer NOT NULL,
  book_id      integer NOT NULL,
  taken_date   date    NOT NULL,
  brought_date date
);


ALTER TABLE borrows OWNER TO postgres;

--
-- Name: borrows_borrow_id_seq; Type: SEQUENCE; Schema: new_schema; Owner: postgres
--

CREATE SEQUENCE borrows_borrow_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE borrows_borrow_id_seq OWNER TO postgres;

--
-- Name: borrows_borrow_id_seq; Type: SEQUENCE OWNED BY; Schema: new_schema; Owner: postgres
--

ALTER SEQUENCE borrows_borrow_id_seq OWNED BY borrows.borrow_id;

--
-- Name: genres; Type: TABLE; Schema: new_schema; Owner: postgres
--

CREATE TABLE genres (
  id   integer               NOT NULL,
  name character varying(60) NOT NULL
);


ALTER TABLE genres OWNER TO postgres;

--
-- Name: genres_id_seq; Type: SEQUENCE; Schema: new_schema; Owner: postgres
--

CREATE SEQUENCE genres_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE genres_id_seq OWNER TO postgres;

--
-- Name: genres_id_seq; Type: SEQUENCE OWNED BY; Schema: new_schema; Owner: postgres
--

ALTER SEQUENCE genres_id_seq OWNED BY genres.id;

--
-- Name: readers; Type: TABLE; Schema: new_schema; Owner: postgres
--

CREATE TABLE readers (
  reader_id integer                NOT NULL,
  name      character varying(120) NOT NULL,
  gender    boolean                NOT NULL,
  dob       date,
  point     integer
);


ALTER TABLE readers OWNER TO postgres;

--
-- Name: readers_reader_id_seq; Type: SEQUENCE; Schema: new_schema; Owner: postgres
--

CREATE SEQUENCE readers_reader_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE readers_reader_id_seq OWNER TO postgres;

--
-- Name: readers_reader_id_seq; Type: SEQUENCE OWNED BY; Schema: new_schema; Owner: postgres
--

ALTER SEQUENCE readers_reader_id_seq OWNED BY readers.reader_id;

--
-- Name: authors id; Type: DEFAULT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY authors ALTER COLUMN id SET DEFAULT nextval ('authors_id_seq'::regclass);

--
-- Name: books book_id; Type: DEFAULT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY books ALTER COLUMN book_id SET DEFAULT nextval ('books_id_seq'::regclass);

--
-- Name: borrows borrow_id; Type: DEFAULT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY borrows ALTER COLUMN borrow_id SET DEFAULT nextval ('borrows_borrow_id_seq'::regclass);

--
-- Name: genres id; Type: DEFAULT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY genres ALTER COLUMN id SET DEFAULT nextval ('genres_id_seq'::regclass);

--
-- Name: readers reader_id; Type: DEFAULT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY readers ALTER COLUMN reader_id SET DEFAULT nextval ('readers_reader_id_seq'::regclass);

--
-- Data for Name: authors; Type: TABLE DATA; Schema: new_schema; Owner: postgres
--

INSERT INTO authors (id, name, dob, rank)
VALUES (2, 'Douglas E. Richards', '1964-01-09', 0);
INSERT INTO authors (id, name, dob, rank)
VALUES (3, 'Rupi Kaur', '1983-12-20', 0);
INSERT INTO authors (id, name, dob, rank)
VALUES (4, 'Ray Bradbury', '1920-08-22', 0);
INSERT INTO authors (id, name, dob, rank)
VALUES (1, 'Chris McMullen', '1970-07-15', 5);
INSERT INTO authors (id, name, dob, rank)
VALUES (6, 'Jerom K. Jerom', '1999-07-15', 0);

--
-- Name: authors_id_seq; Type: SEQUENCE SET; Schema: new_schema; Owner: postgres
--

SELECT pg_catalog.setval('authors_id_seq', 6, true);

--
-- Data for Name: books; Type: TABLE DATA; Schema: new_schema; Owner: postgres
--

INSERT INTO books (book_id, title, pages, count, author, genre, year_publishing)
VALUES (1, 'Algebra Essentials Practice Workbook with Answers', 600, 1, 1, 15, '2013-07-15');
INSERT INTO books (book_id, title, pages, count, author, genre, year_publishing)
VALUES (2, 'Split second', 230, 1, 2, 4, '2015-07-10');
INSERT INTO books (book_id, title, pages, count, author, genre, year_publishing)
VALUES (3, 'Milk and Honey', 316, 1, 3, 17, '2013-10-15');
INSERT INTO books (book_id, title, pages, count, author, genre, year_publishing)
VALUES (4, 'Fahrenheit 451 ', 146, 1, 4, 29, '1953-07-16');

--
-- Name: books_id_seq; Type: SEQUENCE SET; Schema: new_schema; Owner: postgres
--

SELECT pg_catalog.setval('books_id_seq', 4, true);

--
-- Data for Name: borrows; Type: TABLE DATA; Schema: new_schema; Owner: postgres
--


--
-- Name: borrows_borrow_id_seq; Type: SEQUENCE SET; Schema: new_schema; Owner: postgres
--

SELECT pg_catalog.setval('borrows_borrow_id_seq', 1, false);

--
-- Data for Name: genres; Type: TABLE DATA; Schema: new_schema; Owner: postgres
--

INSERT INTO genres (id, name)
VALUES (1, 'Science fiction');
INSERT INTO genres (id, name)
VALUES (2, 'Satire');
INSERT INTO genres (id, name)
VALUES (3, 'Drama');
INSERT INTO genres (id, name)
VALUES (4, 'Action and Adventure');
INSERT INTO genres (id, name)
VALUES (5, 'Romance');
INSERT INTO genres (id, name)
VALUES (6, 'Mystery');
INSERT INTO genres (id, name)
VALUES (7, 'Self help');
INSERT INTO genres (id, name)
VALUES (8, 'Health');
INSERT INTO genres (id, name)
VALUES (9, 'Guide');
INSERT INTO genres (id, name)
VALUES (10, 'Travel');
INSERT INTO genres (id, name)
VALUES (11, 'Children''s');
INSERT INTO genres (id, name)
VALUES (12, 'Religions');
INSERT INTO genres (id, name)
VALUES (13, 'Science');
INSERT INTO genres (id, name)
VALUES (14, 'History');
INSERT INTO genres (id, name)
VALUES (15, 'Math');
INSERT INTO genres (id, name)
VALUES (16, 'Anthology');
INSERT INTO genres (id, name)
VALUES (17, 'Poetry');
INSERT INTO genres (id, name)
VALUES (18, 'Encyclopedias');
INSERT INTO genres (id, name)
VALUES (19, 'Dictionaries');
INSERT INTO genres (id, name)
VALUES (20, 'Comics');
INSERT INTO genres (id, name)
VALUES (21, 'Art');
INSERT INTO genres (id, name)
VALUES (22, 'Cookbooks');
INSERT INTO genres (id, name)
VALUES (23, 'Diaries');
INSERT INTO genres (id, name)
VALUES (24, 'Journals');
INSERT INTO genres (id, name)
VALUES (25, 'Prayer books');
INSERT INTO genres (id, name)
VALUES (26, 'Series');
INSERT INTO genres (id, name)
VALUES (27, 'Trilogies');
INSERT INTO genres (id, name)
VALUES (28, 'Biographies');
INSERT INTO genres (id, name)
VALUES (29, 'Fantasy');

--
-- Name: genres_id_seq; Type: SEQUENCE SET; Schema: new_schema; Owner: postgres
--

SELECT pg_catalog.setval('genres_id_seq', 30, false);

--
-- Data for Name: readers; Type: TABLE DATA; Schema: new_schema; Owner: postgres
--


--
-- Name: readers_reader_id_seq; Type: SEQUENCE SET; Schema: new_schema; Owner: postgres
--

SELECT pg_catalog.setval('readers_reader_id_seq', 1, false);

--
-- Name: authors authors_pkey; Type: CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY authors
ADD CONSTRAINT authors_pkey PRIMARY KEY (id);

--
-- Name: books books_pkey; Type: CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY books
ADD CONSTRAINT books_pkey PRIMARY KEY (book_id);

--
-- Name: borrows borrows_pkey; Type: CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY borrows
ADD CONSTRAINT borrows_pkey PRIMARY KEY (borrow_id);

--
-- Name: genres genres_pkey; Type: CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY genres
ADD CONSTRAINT genres_pkey PRIMARY KEY (id);

--
-- Name: readers readers_pkey; Type: CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY readers
ADD CONSTRAINT readers_pkey PRIMARY KEY (reader_id);

--
-- Name: genres_name_uindex; Type: INDEX; Schema: new_schema; Owner: postgres
--

CREATE UNIQUE INDEX genres_name_uindex
  ON genres USING btree (name);

--
-- Name: books books_authors_id_fk; Type: FK CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY books
ADD CONSTRAINT books_authors_id_fk FOREIGN KEY (author) REFERENCES authors(id);

--
-- Name: books books_genres_id_fk; Type: FK CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY books
ADD CONSTRAINT books_genres_id_fk FOREIGN KEY (genre) REFERENCES genres(id);

--
-- Name: borrows borrows_books_book_id_fk; Type: FK CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY borrows
ADD CONSTRAINT borrows_books_book_id_fk FOREIGN KEY (book_id) REFERENCES books(book_id);

--
-- Name: borrows borrows_readers_reader_id_fk; Type: FK CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY borrows
ADD CONSTRAINT borrows_readers_reader_id_fk FOREIGN KEY (reader_id) REFERENCES readers(reader_id);

--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA
public TO PUBLIC;

--
-- PostgreSQL database dump complete
--

