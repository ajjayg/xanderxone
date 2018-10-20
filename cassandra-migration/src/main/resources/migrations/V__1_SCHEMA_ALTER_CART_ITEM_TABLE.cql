// DROP deal table
DROP TABLE IF EXISTS cart_item;
//Create cart_item table with new Primary key
CREATE TABLE cart_item (acc_code text,sku text,cart_id text,master_id UUID,price double,quantity double,pp_id text,rpp_id text,customer_id text,customer_email text,customer_phone text,id TIMEUUID,checkout_time TIMESTAMP,created_on TIMESTAMP, PRIMARY KEY ((acc_code, sku), cart_id, price, quantity)) ;