CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE IF NOT EXISTS author_tb(
    author_id UUID PRIMARY KEY DEFAULT uuid_generate_v4() ,
    author_name VARCHAR(200) NOT NULL ,
    author_age INT NOT NULL ,
    author_active VARCHAR(200) NOT NULL
);
CREATE TABLE IF NOT EXISTS book_tb(
    book_id UUID PRIMARY KEY DEFAULT uuid_generate_v4() ,
    title VARCHAR(200) NOT NULL ,
    release_year DATE NOT NULL,
    author_id UUID REFERENCES author_tb(author_id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS category_tb(
    category_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    category_name VARCHAR(200) NOT NULL
);
CREATE TABLE IF NOT EXISTS category_book_tb(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    category_id UUID REFERENCES category_tb(category_id) ON DELETE CASCADE ON UPDATE CASCADE ,
    book_id UUID REFERENCES book_tb(book_id) ON DELETE CASCADE ON UPDATE CASCADE
);