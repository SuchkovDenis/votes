insert into roles
values (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

-- ALL passwords: password
insert into users
values (1, 'user1', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
       (2, 'user2', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
       (3, 'user3', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
       (4, 'user4', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
       (5, 'user5', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
       (6, 'admin', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
       (7, 'admin_and_user', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6');

insert into users_roles
values (1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 2), (7, 1), (7, 2);

insert into restaurants
values (1, 'Fish restaurant'),
       (2, 'Steak house');

insert into dishes
values (1, 'Today Fish 1', 10.0, CURRENT_DATE(), 1),
       (2, 'Today Fish 2', 20.0, CURRENT_DATE(), 1),
       (3, 'Today Fish 3', 30.0, CURRENT_DATE(), 1),
       (4, 'Today Fish 4', 40.0, CURRENT_DATE(), 1),
       (5, 'Yesterday Fish 1', 10.0, DATEADD('DAY',-1, CURRENT_DATE()), 1),
       (6, 'Yesterday Fish 2', 20.0, DATEADD('DAY',-1, CURRENT_DATE()), 1),
       (7, 'Yesterday Fish 3', 30.0, DATEADD('DAY',-1, CURRENT_DATE()), 1),
       (8, 'Today Steak 1', 100.0, CURRENT_DATE(), 2),
       (9, 'Today Steak 2', 200.0, CURRENT_DATE(), 2),
       (10, 'Yesterday Steak 1', 110.0, DATEADD('DAY',-1, CURRENT_DATE()), 2),
       (11, 'Yesterday Steak 2', 220.0, DATEADD('DAY',-1, CURRENT_DATE()), 2);

insert into votes
values (1, DATEADD('DAY',-1, CURRENT_TIMESTAMP), 1, 1),
       (2, DATEADD('DAY',-1, CURRENT_TIMESTAMP), 2, 1),
       (3, DATEADD('DAY',-1, CURRENT_TIMESTAMP), 3, 1),
       (4, DATEADD('DAY',-1, CURRENT_TIMESTAMP), 4, 2),
       (5, DATEADD('DAY',-1, CURRENT_TIMESTAMP), 5, 2);