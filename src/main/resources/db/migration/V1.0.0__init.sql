create table dog (
     id numeric(19,0) constraint pk_dog primary key,
     name varchar(128) not null,
     date_of_birth date not null,
     breed varchar(128) not null,
     owner_id numeric(19,0) not null
);

create sequence dog_seq increment by 20 minvalue 10000 maxvalue 999999999999999999 cache 20;