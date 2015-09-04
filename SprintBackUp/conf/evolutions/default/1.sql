# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        bigint auto_increment not null,
  email                     varchar(100),
  password                  varchar(100),
  name                      varchar(100),
  lastName                  varchar(100),
  role                      integer(1),
  yearOfBirth               date,
  gender                    integer,
  skypeName                 varchar(100),
  facebookProfile           varchar(300),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

