USE shop;
LOAD DATA LOCAL INFILE '/docker-entrypoint-initdb.d/data-orderline.csv'
INTO TABLE orderLines 
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
(product_id, order_id,quantity);