create table human
(
    Id             SERIAL PRIMARY KEY,
    First_name     varchar(20) NOT NULL,
    Last_name      varchar(20) NOT NULL,
    Age            INT
        CONSTRAINT human_age_check CHECK (Age > 0 AND Age < 120),
    Driver_license BOOLEAN     NOT NULL,
    car_id INT NOT NULL REFERENCES car(Id)

);
create table car
(
    Id    SERIAL PRIMARY KEY,
    Brand VARCHAR(20) NOT NULL,
    Model VARCHAR(20) NOT NULL,
    Price INT
);

