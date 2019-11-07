USE shop;
LOAD DATA LOCAL INFILE '/docker-entrypoint-initdb.d/data-clientorder.csv'
INTO TABLE clientOrders 
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
( username , order_date);