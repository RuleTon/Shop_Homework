create table products
(
    id         bigserial primary key,
    title      varchar(255),
    price      int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into products (title, price)
values ('Bread', 24),
       ('Milk', 65),
       ('Pen', 32),
       ('Sugar', 33),
       ('Apple', 76),
       ('PineApple', 345),
       ('Cola', 54),
       ('Beer', 72),
       ('Vodka', 94),
       ('Boiled Water', 38),
       ('Yogurt', 78),
       ('Crackers', 28),
       ('Snack', 16),
       ('Eggs', 25),
       ('Juice', 96),
       ('Cat Food', 11);


create table order_products
(
    id             bigserial primary key,
    title          varchar(255),
    quantity       int,
    price_per_item int,
    price          int
);


create table role_table
(
    id   serial      not null
        constraint role_table_pk
            primary key,
    name varchar(20) not null
);

create table user_table
(
    id       serial not null
        constraint user_table_pk
            primary key,
    login    varchar(50),
    password varchar(500),
    role_id  integer
        constraint user_table_role_table_id_fk
            references role_table
);

create
unique index user_table_login_uindex
    on user_table (login);

insert into role_table(name) values ('ROLE_ADMIN');
insert into role_table(name) values ('ROLE_USER');