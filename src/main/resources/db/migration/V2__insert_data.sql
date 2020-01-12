insert into roles
values
       (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

insert into users
values (1, 'admin', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
       (2, 'user', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6');

insert into users_roles
values (1, 1),
       (2,2);