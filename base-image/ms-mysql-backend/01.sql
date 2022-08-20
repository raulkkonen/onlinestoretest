-- create databases
CREATE DATABASE IF NOT EXISTS `productDB`;
CREATE DATABASE IF NOT EXISTS `invoiceDB`;
CREATE DATABASE IF NOT EXISTS `securityDB`;
CREATE DATABASE IF NOT EXISTS `orderDB`;



GRANT ALL ON `productDB`.* TO 'user_store'@'%';
GRANT ALL ON `invoiceDB`.* TO 'user_store'@'%';
GRANT ALL ON `securityDB`.* TO 'user_store'@'%';
GRANT ALL ON `orderDB`.* TO 'user_store'@'%';