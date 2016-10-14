/**
 *
 * @author bittar_ikeima
 */

DROP DATABASE IF EXISTS pizza_orders;

CREATE DATABASE pizza_orders;

USE pizza_orders;

DROP TABLE IF EXISTS OrderInfo;

CREATE TABLE OrderInfo (
    id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    pizzaSize VARCHAR(10) NOT NULL,
    toppings VARCHAR(50),
    delivery BOOLEAN NOT NULL,
    price DOUBLE NOT NULL,

    PRIMARY KEY(id)
);