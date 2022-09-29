DROP TABLE IF EXISTS PRODUCT_MODEL;

CREATE TABLE PRODUCT_MODEL (
    id long AUTO_INCREMENT primary key,
    name varchar(255) not null,
    description varchar(255) not null,
    price double not null,
);