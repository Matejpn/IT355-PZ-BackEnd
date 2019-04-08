create database shopping;
use shopping;
CREATE TABLE users (
  id_users INTEGER   NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(255)    ,
  lastname VARCHAR(255)    ,
  adress VARCHAR(255)    ,
  email VARCHAR(255)    ,
  pass VARCHAR(255)      ,
PRIMARY KEY(id_users));




CREATE TABLE role (
  id_role INTEGER   NOT NULL AUTO_INCREMENT,
  name VARCHAR(45)      ,
PRIMARY KEY(id_role));




CREATE TABLE brand (
  id_brand INTEGER   NOT NULL AUTO_INCREMENT,
  name VARCHAR(255)      ,
PRIMARY KEY(id_brand));




CREATE TABLE category (
  id_category INTEGER   NOT NULL AUTO_INCREMENT,
  name VARCHAR(255)      ,
PRIMARY KEY(id_category));




CREATE TABLE payment (
  id_payment INTEGER   NOT NULL AUTO_INCREMENT,
  users_id_users INTEGER   NOT NULL ,
  date DATETIME      ,
PRIMARY KEY(id_payment)  ,
  FOREIGN KEY(users_id_users)
    REFERENCES users(id_users));


CREATE INDEX payment_FKIndex1 ON payment (users_id_users);


CREATE INDEX IFK_Rel_07 ON payment (users_id_users);


CREATE TABLE role_has_users (
  role_id_role INTEGER   NOT NULL ,
  users_id_users INTEGER   NOT NULL   ,
PRIMARY KEY(role_id_role, users_id_users)    ,
  FOREIGN KEY(role_id_role)
    REFERENCES role(id_role),
  FOREIGN KEY(users_id_users)
    REFERENCES users(id_users));


CREATE INDEX role_has_users_FKIndex1 ON role_has_users (role_id_role);
CREATE INDEX role_has_users_FKIndex2 ON role_has_users (users_id_users);


CREATE INDEX IFK_Rel_01 ON role_has_users (role_id_role);
CREATE INDEX IFK_Rel_02 ON role_has_users (users_id_users);


CREATE TABLE product (
  id_product INTEGER   NOT NULL AUTO_INCREMENT,
  brand_id_brand INTEGER   NOT NULL ,
  category_id_category INTEGER   NOT NULL ,
  name VARCHAR(255)    ,
  price INTEGER    ,
  url VARCHAR(255)    ,
  gender VARCHAR(45)    ,
  isDeleted TINYINT  DEFAULT 0    ,
PRIMARY KEY(id_product)    ,
  FOREIGN KEY(category_id_category)
    REFERENCES category(id_category),
  FOREIGN KEY(brand_id_brand)
    REFERENCES brand(id_brand));


CREATE INDEX item_FKIndex1 ON product (category_id_category);
CREATE INDEX item_FKIndex2 ON product (brand_id_brand);


CREATE INDEX IFK_Rel_03 ON product (category_id_category);
CREATE INDEX IFK_Rel_04 ON product (brand_id_brand);


CREATE TABLE item (
  id_item INTEGER   NOT NULL AUTO_INCREMENT,
  payment_id_payment INTEGER   NOT NULL ,
  product_id_product INTEGER   NOT NULL ,
  quantity INTEGER      ,
PRIMARY KEY(id_item)    ,
  FOREIGN KEY(product_id_product)
    REFERENCES product(id_product),
  FOREIGN KEY(payment_id_payment)
    REFERENCES payment(id_payment));


CREATE INDEX item_FKIndex1 ON item (product_id_product);
CREATE INDEX item_FKIndex2 ON item (payment_id_payment);


CREATE INDEX IFK_Rel_05 ON item (product_id_product);
CREATE INDEX IFK_Rel_06 ON item (payment_id_payment);


