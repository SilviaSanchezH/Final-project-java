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
(1, 1, 'MALE', 'Sanchez', 'Lopez', 'Pablo', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852741', 'pablito'),
(2, 1, 'MALE', 'Garcia', 'Aguado', 'Julio', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852541', 'julito'),
(3, 2, 'FEMALE', 'Rodriguez', 'Lopez', 'Laura', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852741', 'laurita'),
(4, 2, 'FEMALE', 'Lopez', 'Aguado', 'Irene', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852541', 'irenerex'),
(5, 1, 'MALE', 'Sanchez', 'Lopez', 'Paco', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852741', 'paquito'),
(6, 1, 'MALE', 'Ruiz', 'Aguado', 'Enrique', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852541', 'enrique01'),
(7, 2, 'FEMALE', 'Toledo', 'Lopez', 'Mila', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852741', 'miluxi'),
(8, 2, 'FEMALE', 'Alvarez', 'Aguado', 'Celia', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852541', 'celiia');

INSERT INTO `worker` (occupation, professional_number, id) VALUES 
('Admin', '987', 5),
('Auxiliar', '987', 6),
('Enfermero', '987', 7),
('Animador', '987', 8);

INSERT INTO `client` (address, age, city, id) VALUES 
('Calle Alcalá nº12', 78, 'Madrid', 1),
('Calle Platino nº12', 68, 'Madrid', 2),
('Calle Merino nº12', 98, 'Madrid', 3),
('Calle Forja nº10', 74, 'Madrid', 4);

INSERT INTO `role` (id, `name`, user_id) VALUES 
(1, 'CLIENT', 1),
(2, 'CLIENT', 2),
(3, 'CLIENT', 3),
(4, 'CLIENT', 4),
(5, 'WORKER', 5),
(6, 'WORKER', 6),
(7, 'WORKER', 7),
(8, 'WORKER', 8);