DROP SCHEMA IF EXISTS activities_test;
CREATE SCHEMA activities_test;
USE activities_test;

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