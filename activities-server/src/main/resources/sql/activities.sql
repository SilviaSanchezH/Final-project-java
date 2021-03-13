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
(4, 2, '2021/04/30', 'Excursión al campito', '5:00 PM','Vamos a pasar un bonito día a la pradera' , 'LOCAL_TOUR'),
(5, 2, '2021/03/28', 'Cartas', '5:00 PM', 'Vamos a pasar una tarde divertida jugando al bingo!', 'INSIDE'),
(6, 2, '2021/06/28', 'Viaje a Benidorm', '5:00 PM', 'Vamos a hacer un viaje a Benidorm!', 'TRIP');

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


