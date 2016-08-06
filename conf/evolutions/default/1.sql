# --- !Ups


  create table "markets" ("id" BIGINT auto_increment NOT NULL PRIMARY KEY,"name" VARCHAR NOT NULL,"desc" VARCHAR NOT NULL,"created" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);

create table "product_types" ("id" BIGINT auto_increment NOT NULL PRIMARY KEY,"name" VARCHAR NOT NULL,"created" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);

create table "users" ("id" BIGINT auto_increment NOT NULL PRIMARY KEY,"username" VARCHAR NOT NULL,"mail" VARCHAR NOT NULL,"password" VARCHAR NOT NULL,"user_type" VARCHAR NOT NULL,"market_id" BIGINT NOT NULL,"created" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);

create table "products" ("id" BIGINT auto_increment NOT NULL PRIMARY KEY,"wanted_user_id" BIGINT NOT NULL,"product_type_id" BIGINT NOT NULL,"product_quantity" BIGINT NOT NULL,"product_constant_" DOUBLE NOT NULL,"product_exponential" DOUBLE NOT NULL,"created" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);

create table "offers" ("id" BIGINT auto_increment NOT NULL PRIMARY KEY,"market_id" BIGINT NOT NULL,"off_product_id" BIGINT NOT NULL,"off_amount" BIGINT NOT NULL,"wanted_user_id" BIGINT NOT NULL,"wanted_product_id" BIGINT NOT NULL,"wanted_amount" BIGINT NOT NULL,"created" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);

create table "transactions" ("id" BIGINT auto_increment NOT NULL PRIMARY KEY,"desc" VARCHAR NOT NULL,"off_user_id" BIGINT NOT NULL,"off_product_id" BIGINT NOT NULL,"off_amount" BIGINT NOT NULL,"off_marginal" DOUBLE NOT NULL,"wanted_user_id" BIGINT NOT NULL,"wanted_product_id" BIGINT NOT NULL,"wanted_amount" BIGINT NOT NULL,"wanted_marginal" DOUBLE NOT NULL,"created" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);
# --- !Downs
;
drop table "transactions";
drop table "offers";
drop table "products";
drop table "users";
drop table "product_types";
drop table "markets";