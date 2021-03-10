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
  PRIMARY KEY (`id`)
);

CREATE TABLE `client` (
  `client_id` bigint NOT NULL,
  `activity_id` bigint DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  KEY `FK44tiunxg0pe156hf4wcsyg9ya` (`activity_id`),
  FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
);

CREATE TABLE `worker` (
  `worker_id` bigint NOT NULL,
  `activity_id` bigint DEFAULT NULL,
  PRIMARY KEY (`worker_id`),
  KEY `FKdv1kavdnxgpjuohxupuir02id` (`activity_id`),
  FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
);

INSERT INTO activity (id, center, `date`, `description`, `time`, title, `type`) VALUES 
(1, 1, '2021/03/15', 'Vamos a pasar un bonito día al campito', '5:00 PM', 'Excursión al campito', 'LOCAL_TOUR');

INSERT INTO `client` (client_id, activity_id) VALUES
(2, 1);

INSERT INTO `worker` (worker_id, activity_id) VALUES
(1, 1);

