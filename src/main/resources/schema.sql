create schema if not exists ecommerce;

drop table if exists PRICES;

create table if not exists PRICES(
    ID  numeric(16,0) not null AUTO_INCREMENT,
    BRAND_ID int not null,
    START_DATE timestamp not null,
    END_DATE timestamp not null,
    PRICE_LIST int not null,
    PRODUCT_ID int not null,
    PRIORITY int not null ,
    PRICE numeric(16,2) default 0 not null ,
    CURRENCY varchar(3) default 'EUR' not null ,
    PRIMARY KEY (ID)
);