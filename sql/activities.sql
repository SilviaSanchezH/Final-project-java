DROP SCHEMA IF EXISTS activities;
CREATE SCHEMA activities;
USE activities;

CREATE TABLE `activity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `center` bigint DEFAULT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `time` time DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `client` (
  `client_id` bigint NOT NULL,
  `activity_id` bigint DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  KEY `FK44tiunxg0pe156hf4wcsyg9ya` (`activity_id`),
  CONSTRAINT `FK44tiunxg0pe156hf4wcsyg9ya` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `worker` (
  `worker_id` bigint NOT NULL,
  `activity_id` bigint DEFAULT NULL,
  PRIMARY KEY (`worker_id`),
  KEY `FKdv1kavdnxgpjuohxupuir02id` (`activity_id`),
  CONSTRAINT `FKdv1kavdnxgpjuohxupuir02id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci