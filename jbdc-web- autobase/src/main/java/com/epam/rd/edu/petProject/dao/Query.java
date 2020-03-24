package com.epam.rd.edu.petProject.dao;

public enum Query {
    CAR_CREATE("INSERT INTO cars (model, number, technical_passport, release_date, fully_Functional) VALUES (?, ?, ?, ?, ?)"),
    CAR_DELETE("DELETE FROM cars WHERE car_id=?"),
    CAR_READ("SELECT * FROM cars WHERE car_id=?"),
    CAR_GET_ALL("SELECT * FROM cars"),
    CAR_UPDATE("UPDATE cars SET model=?, number=?, technical_passport=?, release_date=?, fully_Functional=? WHERE car_id=?"),
    CAR_CREATE_LIST("INSERT INTO cars (model, number, technical_passport, release_date, fully_Functional) VALUES (?, ?, ?, ?, ?)"),
    CAR_UPDATE_LIST("UPDATE cars SET model=?, number=?, technical_passport=?, release_date=?, fully_Functional=? WHERE car_id=?"),

    CITY_CREATE("INSERT INTO cities (name) VALUES (?)"),
    CITY_UPDATE("UPDATE cities SET name=? WHERE city_id=?"),
    CITY_DELETE("DELETE FROM cities WHERE city_id=?"),
    CITY_READ("SELECT * FROM cities WHERE city_id=?"),
    CITY_GET_ALL("SELECT * FROM cities"),
    CITY_CREATE_LIST("INSERT INTO cities (name) VALUES (?)"),
    CITY_UPDATE_LIST("UPDATE cities SET name=? WHERE city_id=?"),

    ORDER_CREATE("INSERT INTO orders (car_model, order_city_from, order_city_to, order_user) VALUES (?, ?, ? ,?)"),
    ORDER_UPDATE("UPDATE orders SET car_model=?, order_city_from=?, order_city_to=?, order_user=? WHERE order_id=?"),
    ORDER_DELETE("DELETE FROM orders WHERE order_id=?"),
    ORDER_READ("SELECT * FROM orders WHERE order_id=?"),
    ORDER_GET_ALL("SELECT * FROM orders"),
    ORDER_CREATE_LIST("INSERT INTO orders (car_model, order_city_from, order_city_to, order_user) VALUES (?, ?, ? ,?)"),
    ORDER_UPDATE_LIST("UPDATE orders SET car_model=?, order_city_from=?, order_city_to=?, order_user=? WHERE order_id=?"),

    TRANSIT_CREATE("INSERT INTO transits (status, transit_city_from, transit_city_to, transit_car, transit_user, transit_driver) VALUES (?, ?, ? ,?, ?, ?)"),
    TRANSIT_UPDATE("UPDATE transits SET status=?, transit_city_from=?, transit_city_to=?, transit_car=?, transit_user=?, transit_driver=? WHERE transit_id=?"),
    TRANSIT_DELETE("DELETE FROM transits WHERE transit_id=?"),
    TRANSIT_READ("SELECT * FROM transits WHERE transit_id=?"),
    TRANSIT_GET_ALL("SELECT * FROM transits"),
    TRANSIT_CREATE_LIST("INSERT INTO transits (status, transit_city_from, transit_city_to, transit_car, transit_user, transit_driver) VALUES (?, ?, ? ,?, ?, ?)"),
    TRANSIT_UPDATE_LIST("UPDATE transits SET status=?, transit_city_from=?, transit_city_to, transit_car=?, transit_user=?, transit_driver=? WHERE transit_id=?"),

    USER_CREATE("INSERT INTO users (name, family_name, login, password, role, email) VALUES (?, ?, ?, ? ,?, ?)"),
    USER_UPDATE("UPDATE users SET name=?, family_name=?, login=?, password=?, role=?, email=? WHERE user_id=?"),
    USER_DELETE("DELETE FROM users WHERE user_id=?"),
    USER_READ("SELECT * FROM users WHERE user_id=?"),
    USER_GET_ALL("SELECT * FROM users"),
    USER_CREATE_LIST("INSERT INTO users (name, family_name, login, password, role, email) VALUES (?, ?, ?, ? ,?, ?)"),
    USER_UPDATE_LIST("UPDATE users SET name=?, family_name=?, login=?, password=?, role=?, email=? WHERE user_id=?"),
    USER_GET_BY_EMAIL("SELECT user_id, name, family_name, login, password, role, email FROM users WHERE email=?"),
    USER_GET_BY_LOGIN("SELECT user_id, name, family_name, login, password, role, email FROM users WHERE login=?");

    private final String query;

    Query(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
