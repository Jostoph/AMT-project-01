USE shop;
LOAD DATA LOCAL INFILE '/docker-entrypoint-initdb.d/data-client.csv'
INTO TABLE clients
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
(username, email, password_hash);