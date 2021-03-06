create table users (
    id bigint auto_increment,
    name varchar(255),
    password varchar(255)
);

create table roles (
    id bigint auto_increment,
    name varchar(255)
);

create table users_roles (
    user_id bigint,
    role_id bigint,
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id)
);

create table restaurants (
    id bigint auto_increment,
    name varchar(255)
);

create table dishes (
    id bigint auto_increment,
    name varchar(255),
    price double,
    date date,
    restaurant_id bigint,
    foreign key (restaurant_id) references restaurants(id)
);

create table votes (
   id bigint auto_increment,
   date_time datetime,
   user_id bigint,
   restaurant_id bigint,
   foreign key (user_id) references users(id),
   foreign key (restaurant_id) references restaurants(id)
);