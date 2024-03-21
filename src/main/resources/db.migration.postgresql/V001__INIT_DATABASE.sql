CREATE TABLE IF NOT EXISTS author (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS book (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS genre (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS user_ (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS books_authors (
    book_id INTEGER REFERENCES book(id),
    author_id INTEGER REFERENCES author(id),
    PRIMARY KEY (book_id, author_id)
);

CREATE TABLE IF NOT EXISTS books_genres (
    book_id INTEGER REFERENCES book(id),
    genre_id INTEGER REFERENCES genre(id),
    PRIMARY KEY (book_id, genre_id)
);

CREATE TABLE IF NOT EXISTS user_favorite_books (
    user_id INTEGER REFERENCES user_(id),
    book_id INTEGER REFERENCES book(id),
    PRIMARY KEY (user_id, book_id)
);