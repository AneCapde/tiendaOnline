CREATE DATABASE tiendaonline;
CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';
GRANT ALL ON jersey.* TO 'spq'@'localhost';