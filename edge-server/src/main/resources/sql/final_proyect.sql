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
(2, 1, 'FEMALE', 'Garcia', 'Aguado', 'Laura', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852541', 'laurita'),
(3, 2, 'MALE', 'Rodriguez', 'Lopez', 'Julio', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852741', 'julito'),
(4, 2, 'FEMALE', 'Lopez', 'Aguado', 'Irene', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852541', 'irenerex'),
(5, 1, 'MALE', 'Sanchez', 'Lopez', 'Paco', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852741', 'paquito'),
(6, 1, 'FEMALE', 'Ruiz', 'Aguado', 'Mila', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852541', 'miluxi'),
(7, 2, 'MALE', 'Toledo', 'Lopez', 'Enrique', '$2a$10$.zKShNvvjf5izYLCdYvKBe/l15/x5CoyWK1RFM/xsGLy/kdOHmm.K', '963852741', 'enrique01'),
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

DROP SCHEMA IF EXISTS center;
CREATE SCHEMA center;
USE center;

CREATE TABLE `center` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `center` (id, address, city, name, phone_number) VALUES 
(1, 'Calle oxígeno, nº4', 'Madrid', 'Centro Mayores Arbolitos', '919987456'),
(2, 'Calle laguna, nº9', 'Madrid', 'Centro Mayores Alegría', '919987476');

DROP SCHEMA IF EXISTS activities;
CREATE SCHEMA activities;
USE activities;

CREATE TABLE `activity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `center` bigint DEFAULT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `client_id` bigint,
  PRIMARY KEY (`id`)
);

CREATE TABLE `client` (
  `client_id` bigint NOT NULL,
  PRIMARY KEY (`client_id`)
);

CREATE TABLE `worker` (
  `worker_id` bigint NOT NULL,
  PRIMARY KEY (`worker_id`)
);

CREATE TABLE `activities_clients` (
  `activity_id` bigint NOT NULL,
  `client_id` bigint NOT NULL,
  KEY (`client_id`),
  KEY (`activity_id`),
  FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
);

CREATE TABLE `activities_workers` (
  `activity_id` bigint NOT NULL,
  `worker_id` bigint NOT NULL,
  KEY (`worker_id`),
  KEY (`activity_id`),
  FOREIGN KEY (`worker_id`) REFERENCES `worker` (`worker_id`),
  FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
);

INSERT INTO activity (id, center, `date`, title, `time`, `description`, `type`) VALUES
(1, 1, '2021/04/30', 'Excursión al campito', '5:00 PM', 'Vamos a pasar un bonito día al campito' , 'LOCAL_TOUR'),
(2, 1, '2021/03/28', 'Bingo', '5:00 PM', 'Vamos a pasar una tarde divertida jugando al bingo!', 'INSIDE'),
(3, 1, '2021/06/28', 'Viaje a Benidorm', '5:00 PM', 'Vamos a hacer un viaje a Benidorm!', 'TRIP'),
(4, 2, '2021/04/30', 'Excursión al Museo del Prado', '5:00 PM','Vamos a visitar el museo del Prado' , 'LOCAL_TOUR'),
(5, 2, '2021/03/28', 'Cartas', '5:00 PM', 'Vamos a pasar una tarde divertida jugando al bingo!', 'INSIDE'),
(6, 2, '2021/06/28', 'Viaje a Cartagena', '5:00 PM', 'Vamos a hacer un viaje a Cartagena!', 'TRIP');

INSERT INTO `client` (client_id) VALUES
(1),
(2),
(3),
(4);

INSERT INTO `worker` (worker_id) VALUES
(5),
(6),
(7),
(8);

INSERT INTO `activities_clients` (`activity_id`, `client_id`) VALUES 
(1,1),
(1,2),
(2,1),
(3,2),
(4,3),
(4,4),
(5,3),
(6,4);

INSERT INTO `activities_workers` (`activity_id`,  `worker_id`) VALUES 
(1,5),
(1,6),
(2,6),
(3,5),
(4,7),
(4,8),
(5,7),
(6,8);
