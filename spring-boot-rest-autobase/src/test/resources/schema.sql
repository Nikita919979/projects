DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS transits;

create table users
(
    user_uuid   VARCHAR(36)
        primary key,
    name        varchar(60)  not null,
    family_name varchar(60)  not null,
    username    varchar(60)  not null,
    password    varchar(256) not null,
    role        varchar(60)  not null,
    email       varchar(60)  not null
);

create table cars
(
    car_uuid           VARCHAR(36)
        primary key,
    model              varchar(60) not null,
    number             varchar(60) not null,
    technical_passport varchar(60) not null,
    release_date       timestamp   not null,
    fully_Functional   tinyint     not null
);

create table cities
(
    city_uuid bigint auto_increment
        primary key,
    name      varchar(60) not null
);

create table orders
(
    order_uuid      VARCHAR(36)
        primary key,
    car_model       varchar(60) not null,
    order_city_from bigint      not null,
    order_city_to   bigint      not null,
    order_user      bigint      not null,
    constraint fk_order_city_from
        foreign key (order_city_from) references cities (city_id),
    constraint fk_order_city_to
        foreign key (order_city_to) references cities (city_id),
    constraint fk_order_user
        foreign key (order_user) references users (user_id)
);

create table transits
(
    transit_uuid      VARCHAR(36)
        primary key,
    status            varchar(60) not null,
    transit_city_from bigint      not null,
    transit_city_to   bigint      not null,
    transit_car       bigint      not null,
    transit_user      bigint      not null,
    transit_driver    bigint      not null,
    constraint fk_transit_car
        foreign key (transit_car) references cars (car_id),
    constraint fk_transit_city_from
        foreign key (transit_city_from) references cities (city_id),
    constraint fk_transit_city_to
        foreign key (transit_city_to) references cities (city_id),
    constraint fk_transit_driver
        foreign key (transit_driver) references users (user_id),
    constraint fk_transit_user
        foreign key (transit_user) references users (user_id)
);
