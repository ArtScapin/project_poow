-- Table: public.users

CREATE TABLE users
(
    id SERIAL NOT NULL PRIMARY KEY,
    name varchar(250),
    email varchar(250) UNIQUE,
    password varchar(40)
);

-- Table: public.workspaces

CREATE TABLE workspaces
(
    id SERIAL NOT NULL PRIMARY KEY,
    name varchar(250),
    id_user int,
    FOREIGN KEY (id_user) REFERENCES users(id)
);

-- Table: public.lists

CREATE TABLE lists
(
    id SERIAL NOT NULL PRIMARY KEY,
    name varchar(250),
    id_workspace int,
    FOREIGN KEY (id_workspace) REFERENCES workspaces(id)
);

-- Table: public.itens

CREATE TABLE itens
(
    id SERIAL NOT NULL PRIMARY KEY,
    name varchar(250),
    status boolean,
    id_list int,
    FOREIGN KEY (id_list) REFERENCES lists(id)
);