CREATE TABLE address(
    id SERIAL PRIMARY KEY NOT NULL,
    cep VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    district VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    house_number VARCHAR(10) NOT NULL
);



CREATE TABLE ceremonialist(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    document VARCHAR(14) NOT NULL,
    birthday DATE NOT NULL,
    phone VARCHAR(25) NOT NULL,
    id_address INTEGER REFERENCES address(id)
);

CREATE TABLE client(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    birthday DATE NOT NULL,
    phone VARCHAR(25) NOT NULL,
    id_address INTEGER REFERENCES address(id)
);

CREATE TABLE supplier(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    phone VARCHAR(25) NOT NULL,
    service_type VARCHAR(30) NOT NULL,
    description TEXT NOT NULL, 
    id_address INTEGER REFERENCES address(id)
);


CREATE TABLE item(
    id SERIAL PRIMARY KEY NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description TEXT NOT NULL,  
    id_supplier INTEGER REFERENCES supplier(id)
);

CREATE TABLE budget(
    id SERIAL PRIMARY KEY NOT NULL,
    buget_date DATE NOT NULL,
    id_cerimonialist INTEGER REFERENCES ceremonialist(id),
    id_client INTEGER REFERENCES client(id),
    id_itens INTEGER REFERENCES item(id)
);


CREATE TABLE contract(
    id SERIAL PRIMARY KEY NOT NULL,
    pdf TEXT NOT NULL,
    id_buget INTEGER REFERENCES budget(id)
);