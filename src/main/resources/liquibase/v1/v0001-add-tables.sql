create table "author"
("id" uuid primary key,
"name" varchar(255),
"age" int
);

create table "book"
("id" uuid primary key,
 "name" varchar(255),
 "pages" int,
 "author_id" uuid);
