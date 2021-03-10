DROP SCHEMA IF EXISTS user;
CREATE SCHEMA user;
USE user;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `center` bigint DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `second_surname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `client` (
  `address` varchar(255) DEFAULT NULL,
  `age` int NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);

CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY  (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `worker` (
  `occupation` varchar(255) DEFAULT NULL,
  `professional_number` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);

INSERT INTO `user` (id, center, gender, last_name, second_surname, name, password, phone_number, username) VALUES 
(1, 1, 'FEMALE', 'Sanchez', 'Iglesias', 'Pablo', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852741', 'pablito'),
(2, 1, 'MALE', 'Iglesias', 'Abascal', 'Julio', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852541', 'julito');

INSERT INTO `worker` (occupation, professional_number, id) VALUES 
('Admin', '987', 1);

INSERT INTO `client` (address, age, city, id) VALUES 
('Calle Alcalá 12', 78, 'Madrid', 2);

INSERT INTO `role` (id, `name`, user_id) VALUES 
(1, 'WORKER', 1),
(2, 'CLIENT', 2);