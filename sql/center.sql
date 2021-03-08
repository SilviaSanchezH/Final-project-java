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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `worker` (
  `worker_id` bigint NOT NULL,
  `center_id` bigint DEFAULT NULL,
  PRIMARY KEY (`worker_id`),
  KEY `FKfbee7npk15simk8hqwjxojagh` (`center_id`),
  CONSTRAINT `FKfbee7npk15simk8hqwjxojagh` FOREIGN KEY (`center_id`) REFERENCES `center` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci