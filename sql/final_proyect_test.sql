DROP SCHEMA IF EXISTS user_test;
CREATE SCHEMA user_test;
USE user_test;

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

DROP SCHEMA IF EXISTS center_test;
CREATE SCHEMA center_test;
USE center_test;

CREATE TABLE `center` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

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