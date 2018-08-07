--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.9
-- Dumped by pg_dump version 9.6.9

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
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
SELECT pg_catalog.set_config('search_path', '', false);
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


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: authors; Type: TABLE; Schema: new_schema; Owner: postgres
--

CREATE TABLE new_schema.authors (
    name character varying(110),
    dob date,
    rank integer DEFAULT 0,
    id integer NOT NULL
);


ALTER TABLE new_schema.authors OWNER TO postgres;

--
-- Name: authors_id_seq; Type: SEQUENCE; Schema: new_schema; Owner: postgres
--

CREATE SEQUENCE new_schema.authors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE new_schema.authors_id_seq OWNER TO postgres;

--
-- Name: authors_id_seq; Type: SEQUENCE OWNED BY; Schema: new_schema; Owner: postgres
--

ALTER SEQUENCE new_schema.authors_id_seq OWNED BY new_schema.authors.id;


--
-- Name: books; Type: TABLE; Schema: new_schema; Owner: postgres
--

CREATE TABLE new_schema.books (
    book_id integer NOT NULL,
    title character varying(100) NOT NULL,
    pages integer,
    count integer,
    author integer,
    genre integer
);


ALTER TABLE new_schema.books OWNER TO postgres;

--
-- Name: books_book_id_seq; Type: SEQUENCE; Schema: new_schema; Owner: postgres
--

CREATE SEQUENCE new_schema.books_book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE new_schema.books_book_id_seq OWNER TO postgres;

--
-- Name: books_book_id_seq; Type: SEQUENCE OWNED BY; Schema: new_schema; Owner: postgres
--

ALTER SEQUENCE new_schema.books_book_id_seq OWNED BY new_schema.books.book_id;


--
-- Name: borrows; Type: TABLE; Schema: new_schema; Owner: postgres
--

CREATE TABLE new_schema.borrows (
    reader_id integer NOT NULL,
    book_id integer NOT NULL,
    date_from date NOT NULL,
    date_till date,
    date_return date,
    comment_id integer
);


ALTER TABLE new_schema.borrows OWNER TO postgres;

--
-- Name: comments; Type: TABLE; Schema: new_schema; Owner: postgres
--

CREATE TABLE new_schema.comments (
    id integer NOT NULL,
    comment character varying(200),
    rating smallint,
    create_on date
);


ALTER TABLE new_schema.comments OWNER TO postgres;

--
-- Name: comments_id_seq; Type: SEQUENCE; Schema: new_schema; Owner: postgres
--

CREATE SEQUENCE new_schema.comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE new_schema.comments_id_seq OWNER TO postgres;

--
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: new_schema; Owner: postgres
--

ALTER SEQUENCE new_schema.comments_id_seq OWNED BY new_schema.comments.id;


--
-- Name: genres; Type: TABLE; Schema: new_schema; Owner: postgres
--

CREATE TABLE new_schema.genres (
    id integer NOT NULL,
    name character varying(60) NOT NULL
);


ALTER TABLE new_schema.genres OWNER TO postgres;

--
-- Name: genres_id_seq; Type: SEQUENCE; Schema: new_schema; Owner: postgres
--

CREATE SEQUENCE new_schema.genres_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE new_schema.genres_id_seq OWNER TO postgres;

--
-- Name: genres_id_seq; Type: SEQUENCE OWNED BY; Schema: new_schema; Owner: postgres
--

ALTER SEQUENCE new_schema.genres_id_seq OWNED BY new_schema.genres.id;


--
-- Name: readers; Type: TABLE; Schema: new_schema; Owner: postgres
--

CREATE TABLE new_schema.readers (
    name character varying(120) NOT NULL,
    rank integer,
    reader_id integer NOT NULL,
    discount smallint
);


ALTER TABLE new_schema.readers OWNER TO postgres;

--
-- Name: readers_reader_id_seq; Type: SEQUENCE; Schema: new_schema; Owner: postgres
--

CREATE SEQUENCE new_schema.readers_reader_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE new_schema.readers_reader_id_seq OWNER TO postgres;

--
-- Name: readers_reader_id_seq; Type: SEQUENCE OWNED BY; Schema: new_schema; Owner: postgres
--

ALTER SEQUENCE new_schema.readers_reader_id_seq OWNED BY new_schema.readers.reader_id;


--
-- Name: authors id; Type: DEFAULT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.authors ALTER COLUMN id SET DEFAULT nextval('new_schema.authors_id_seq'::regclass);


--
-- Name: books book_id; Type: DEFAULT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.books ALTER COLUMN book_id SET DEFAULT nextval('new_schema.books_book_id_seq'::regclass);


--
-- Name: comments id; Type: DEFAULT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.comments ALTER COLUMN id SET DEFAULT nextval('new_schema.comments_id_seq'::regclass);


--
-- Name: genres id; Type: DEFAULT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.genres ALTER COLUMN id SET DEFAULT nextval('new_schema.genres_id_seq'::regclass);


--
-- Name: readers reader_id; Type: DEFAULT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.readers ALTER COLUMN reader_id SET DEFAULT nextval('new_schema.readers_reader_id_seq'::regclass);


--
-- Data for Name: authors; Type: TABLE DATA; Schema: new_schema; Owner: postgres
--

INSERT INTO new_schema.authors VALUES ('Douglas E. Richards', '1964-01-09', 0, 2);
INSERT INTO new_schema.authors VALUES ('Rupi Kaur', '1983-12-20', 0, 3);
INSERT INTO new_schema.authors VALUES ('Chris McMullen', '1970-07-15', 0, 1);
INSERT INTO new_schema.authors VALUES ('Ray Bradbury', '1920-08-22', 0, 4);
INSERT INTO new_schema.authors VALUES ('Stiven King', '1900-05-12', 5, 7);


--
-- Name: authors_id_seq; Type: SEQUENCE SET; Schema: new_schema; Owner: postgres
--

SELECT pg_catalog.setval('new_schema.authors_id_seq', 6, false);


--
-- Data for Name: books; Type: TABLE DATA; Schema: new_schema; Owner: postgres
--

INSERT INTO new_schema.books VALUES (1, 'Algebra Essentials Practice Workbook with Answers', 600, 1, 1, 15);
INSERT INTO new_schema.books VALUES (2, 'Split second', 230, 1, 2, 4);
INSERT INTO new_schema.books VALUES (3, 'Milk and Honey', 316, 1, 3, 17);
INSERT INTO new_schema.books VALUES (8, 'Wild West Men', 312, 1, 4, 30);
INSERT INTO new_schema.books VALUES (7, 'Amazonian people', 345, 1, 4, 30);
INSERT INTO new_schema.books VALUES (4, 'Fahrenheit 451 ', 146, 1, 4, 29);


--
-- Name: books_book_id_seq; Type: SEQUENCE SET; Schema: new_schema; Owner: postgres
--

SELECT pg_catalog.setval('new_schema.books_book_id_seq', 9, true);


--
-- Data for Name: borrows; Type: TABLE DATA; Schema: new_schema; Owner: postgres
--

INSERT INTO new_schema.borrows VALUES (1, 4, '2018-08-07', '2018-08-17', '2018-08-07', 1);


--
-- Data for Name: comments; Type: TABLE DATA; Schema: new_schema; Owner: postgres
--

INSERT INTO new_schema.comments VALUES (1, 'Awesome book! I have never read so much interesting text. I recommend everyone read that book.', 0, '2018-08-07');


--
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: new_schema; Owner: postgres
--

SELECT pg_catalog.setval('new_schema.comments_id_seq', 1, true);


--
-- Data for Name: genres; Type: TABLE DATA; Schema: new_schema; Owner: postgres
--

INSERT INTO new_schema.genres VALUES (1, 'Science fiction');
INSERT INTO new_schema.genres VALUES (2, 'Satire');
INSERT INTO new_schema.genres VALUES (3, 'Drama');
INSERT INTO new_schema.genres VALUES (4, 'Action and Adventure');
INSERT INTO new_schema.genres VALUES (5, 'Romance');
INSERT INTO new_schema.genres VALUES (6, 'Mystery');
INSERT INTO new_schema.genres VALUES (7, 'Self help');
INSERT INTO new_schema.genres VALUES (8, 'Health');
INSERT INTO new_schema.genres VALUES (9, 'Guide');
INSERT INTO new_schema.genres VALUES (10, 'Travel');
INSERT INTO new_schema.genres VALUES (11, 'Children''s');
INSERT INTO new_schema.genres VALUES (12, 'Religions');
INSERT INTO new_schema.genres VALUES (13, 'Science');
INSERT INTO new_schema.genres VALUES (14, 'History');
INSERT INTO new_schema.genres VALUES (15, 'Math');
INSERT INTO new_schema.genres VALUES (16, 'Anthology');
INSERT INTO new_schema.genres VALUES (17, 'Poetry');
INSERT INTO new_schema.genres VALUES (18, 'Encyclopedias');
INSERT INTO new_schema.genres VALUES (19, 'Dictionaries');
INSERT INTO new_schema.genres VALUES (20, 'Comics');
INSERT INTO new_schema.genres VALUES (21, 'Art');
INSERT INTO new_schema.genres VALUES (22, 'Cookbooks');
INSERT INTO new_schema.genres VALUES (23, 'Diaries');
INSERT INTO new_schema.genres VALUES (24, 'Journals');
INSERT INTO new_schema.genres VALUES (25, 'Prayer books');
INSERT INTO new_schema.genres VALUES (26, 'Series');
INSERT INTO new_schema.genres VALUES (27, 'Trilogies');
INSERT INTO new_schema.genres VALUES (28, 'Biographies');
INSERT INTO new_schema.genres VALUES (29, 'Fantasy');
INSERT INTO new_schema.genres VALUES (30, 'Temporal Genre');


--
-- Name: genres_id_seq; Type: SEQUENCE SET; Schema: new_schema; Owner: postgres
--

SELECT pg_catalog.setval('new_schema.genres_id_seq', 31, true);


--
-- Data for Name: readers; Type: TABLE DATA; Schema: new_schema; Owner: postgres
--

INSERT INTO new_schema.readers VALUES ('John Doe', 0, 1, 5);
INSERT INTO new_schema.readers VALUES ('Ralf Vins', 0, 2, 0);
INSERT INTO new_schema.readers VALUES ('Nick McCola', 1, 3, 10);
INSERT INTO new_schema.readers VALUES ('Liz Turner', 0, 4, 2);


--
-- Name: readers_reader_id_seq; Type: SEQUENCE SET; Schema: new_schema; Owner: postgres
--

SELECT pg_catalog.setval('new_schema.readers_reader_id_seq', 1, false);


--
-- Name: authors authors_id_pk; Type: CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.authors
    ADD CONSTRAINT authors_id_pk PRIMARY KEY (id);


--
-- Name: books books_pkey; Type: CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (book_id);


--
-- Name: borrows borrows_pk; Type: CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.borrows
    ADD CONSTRAINT borrows_pk PRIMARY KEY (reader_id, book_id, date_from);


--
-- Name: comments comments_pk; Type: CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.comments
    ADD CONSTRAINT comments_pk PRIMARY KEY (id);


--
-- Name: genres genres_pkey; Type: CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.genres
    ADD CONSTRAINT genres_pkey PRIMARY KEY (id);


--
-- Name: readers readers_reader_id_pk; Type: CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.readers
    ADD CONSTRAINT readers_reader_id_pk PRIMARY KEY (reader_id);


--
-- Name: comments_comment_uindex; Type: INDEX; Schema: new_schema; Owner: postgres
--

CREATE UNIQUE INDEX comments_comment_uindex ON new_schema.comments USING btree (comment);


--
-- Name: books books_authors_id_fk; Type: FK CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.books
    ADD CONSTRAINT books_authors_id_fk FOREIGN KEY (author) REFERENCES new_schema.authors(id);


--
-- Name: books books_genres_id_fk; Type: FK CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.books
    ADD CONSTRAINT books_genres_id_fk FOREIGN KEY (genre) REFERENCES new_schema.genres(id);


--
-- Name: borrows borrows_books_book_id_fk; Type: FK CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.borrows
    ADD CONSTRAINT borrows_books_book_id_fk FOREIGN KEY (book_id) REFERENCES new_schema.books(book_id);


--
-- Name: borrows borrows_comments_id_fk; Type: FK CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.borrows
    ADD CONSTRAINT borrows_comments_id_fk FOREIGN KEY (comment_id) REFERENCES new_schema.comments(id);


--
-- Name: borrows borrows_readers_reader_id_fk; Type: FK CONSTRAINT; Schema: new_schema; Owner: postgres
--

ALTER TABLE ONLY new_schema.borrows
    ADD CONSTRAINT borrows_readers_reader_id_fk FOREIGN KEY (reader_id) REFERENCES new_schema.readers(reader_id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

