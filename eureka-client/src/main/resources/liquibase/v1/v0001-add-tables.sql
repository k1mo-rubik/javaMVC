create table "author"
("id" uuid primary key,
"name" varchar(255),
"age" int,
"deleted" bool,
"created_date" TIMESTAMP with time zone default now()
);

create table "book"
("id" uuid primary key,
 "name" varchar(255),
 "pages" int,
 "author_id" uuid);
