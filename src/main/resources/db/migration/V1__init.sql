drop table if exists users;

create table users (
    id bigint auto_increment,
    name varchar(255),
    password varchar(255)
);

create table roles (
    id bigint auto_increment,
    name varchar(255)
);

create table users_roles
(
    user_id bigint,
    role_id bigint,
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id)
);