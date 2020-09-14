CREATE TABLE IF NOT EXISTS patients
(
    patient_id   bigint IDENTITY PRIMARY KEY,
    first_name   varchar(255) NOT NULL,
    surname      varchar(255) NOT NULL,
    patronymic   varchar(255) NOT NULL,
    phone_number varchar(15)  NOT NULL
);

CREATE TABLE IF NOT EXISTS doctors
(
    doctor_id      bigint IDENTITY PRIMARY KEY,
    first_name     varchar(255) NOT NULL,
    surname        varchar(255) NOT NULL,
    patronymic     varchar(255) NOT NULL,
    specialization varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS recipe_priorities
(
    priority_id   smallint IDENTITY PRIMARY KEY,
    priority_name varchar(63) NOT NULL
);

CREATE TABLE IF NOT EXISTS recipes
(
    recipe_id     bigint IDENTITY PRIMARY KEY,
    description   varchar(255) NOT NULL,
    patient_id    bigint FOREIGN KEY REFERENCES patients,
    doctor_id     bigint FOREIGN KEY REFERENCES doctors,
    creation_date date DEFAULT current_date NOT NULL,
    validity    int NOT NULL,
    priority_id   smallint DEFAULT 1 FOREIGN KEY REFERENCES recipe_priorities
);
