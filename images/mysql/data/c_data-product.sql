USE shop;
LOAD DATA LOCAL INFILE '/docker-entrypoint-initdb.d/data-product.csv'
INTO TABLE products
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
( product_name, price,origin ,description  );