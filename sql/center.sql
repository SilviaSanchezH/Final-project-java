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
(1, 'Calle oxígeno', 'Madrid', 'Centro Mayores Arbolitos', '919987456'),
(2, 'Calle laguna', 'Madrid', 'El centrito', '919987476');