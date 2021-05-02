DROP DATABASE IF EXISTS locationservicedb;
DROP USER IF EXISTS `location_service`@`%`;
CREATE DATABASE IF NOT EXISTS locationservicedb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `location_service`@`%` IDENTIFIED WITH mysql_native_password BY 'P455w0rd12!';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `locationservicedb`.* TO `location_service`@`%`;
FLUSH PRIVILEGES;