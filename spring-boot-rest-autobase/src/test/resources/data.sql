INSERT INTO cars (car_uuid, model, number, technical_passport, release_date, fully_Functional)
VALUES ('8e45a958-71db-11ea-bc55-0242ac130003', 'VAN', '016-01OA', 'vp4Tz6G6',
        '2017-02-18 00:00:00', 0);
INSERT INTO cars (car_uuid, model, number, technical_passport, release_date, fully_Functional)
VALUES ('a84e93e6-71db-11ea-bc55-0242ac130003', 'VOLVO', 'CA128TD', '6mrRr9D1',
        '2014-06-16 00:00:00', 1);
INSERT INTO cars (car_uuid, model, number, technical_passport, release_date, fully_Functional)
VALUES ('ac194ac4-7290-11ea-bc55-0242ac130003', 'MAN', 'testCarNumber1',
        'testCarTechnicalPassport1', '2020-03-23 00:00:00', 1);
INSERT INTO cities (city_uuid, name)
VALUES ('caad8f82-71db-11ea-bc55-0242ac130003', 'Dnepr');
INSERT INTO cities (city_uuid, name)
VALUES ('cfd3fb4a-71db-11ea-bc55-0242ac130003', 'Rome');
INSERT INTO cities (city_uuid, name)
VALUES ('d326965e-71db-11ea-bc55-0242ac130003', 'Moscow');
INSERT INTO users (user_uuid, name, family_name, username, password, role, email)
VALUES ('df9e5624-71db-11ea-bc55-0242ac130003', 'Nikita', 'Poddubskiy', 'admin',
        '25f43b1486ad95a1398e3eeb3d83bc4010015fcc9bedb35b432e00298d5021f7', 'ADMIN',
        'nikita919979@gmail.com');
INSERT INTO users (user_uuid, name, family_name, username, password, role, email)
VALUES ('e3f1bb8a-71db-11ea-bc55-0242ac130003', 'Mike', 'Petrov', 'dispatcher',
        'a8c90fd571c6ceb25cbff41731c638be1392ead817a0c6745723756a5a48408a', 'DISPATCHER',
        'qwerty919979@gmail.com');
INSERT INTO users (user_uuid, name, family_name, username, password, role, email)
VALUES ('317e82f2-7295-11ea-bc55-0242ac130003', 'Jack', 'Pavlov', 'dispatcher1',
        'a8c90fd571c6ceb25cbff41731c638be1392ead817a0c6745723756a5a48408a', 'DISPATCHER',
        'Jack_Pavlov_1@gmail.com');
INSERT INTO orders (order_uuid, car_model, order_city_from, order_city_to, order_user)
VALUES ('e731d0fa-71db-11ea-bc55-0242ac130003', 'MAN', 'caad8f82-71db-11ea-bc55-0242ac130003',
        'cfd3fb4a-71db-11ea-bc55-0242ac130003', 'e3f1bb8a-71db-11ea-bc55-0242ac130003');
INSERT INTO orders (order_uuid, car_model, order_city_from, order_city_to, order_user)
VALUES ('eb3d9184-71db-11ea-bc55-0242ac130003', 'VAN', 'cfd3fb4a-71db-11ea-bc55-0242ac130003',
        'caad8f82-71db-11ea-bc55-0242ac130003', 'e3f1bb8a-71db-11ea-bc55-0242ac130003');
INSERT INTO orders (order_uuid, car_model, order_city_from, order_city_to, order_user)
VALUES ('ee117524-71db-11ea-bc55-0242ac130003', 'MAN', 'caad8f82-71db-11ea-bc55-0242ac130003',
        'cfd3fb4a-71db-11ea-bc55-0242ac130003', 'e3f1bb8a-71db-11ea-bc55-0242ac130003');
INSERT INTO transits (transit_uuid, status, transit_city_from, transit_city_to, transit_car,
                      transit_user, transit_driver)
VALUES ('f1344876-71db-11ea-bc55-0242ac130003', 'OPENED', 'caad8f82-71db-11ea-bc55-0242ac130003',
        'caad8f82-71db-11ea-bc55-0242ac130003', '8e45a958-71db-11ea-bc55-0242ac130003',
        'e3f1bb8a-71db-11ea-bc55-0242ac130003', 'df9e5624-71db-11ea-bc55-0242ac130003');
INSERT INTO transits (transit_uuid, status, transit_city_from, transit_city_to, transit_car,
                      transit_user, transit_driver)
VALUES ('f50eb9e0-71db-11ea-bc55-0242ac130003', 'OPENED', 'cfd3fb4a-71db-11ea-bc55-0242ac130003',
        'cfd3fb4a-71db-11ea-bc55-0242ac130003',
        'a84e93e6-71db-11ea-bc55-0242ac130003', 'df9e5624-71db-11ea-bc55-0242ac130003',
        'e3f1bb8a-71db-11ea-bc55-0242ac130003');
