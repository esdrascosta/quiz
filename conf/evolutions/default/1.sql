# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table alternativa (
  id                        bigint not null,
  pergunta_id               bigint not null,
  descricao                 varchar(255),
  alternativa_certa         boolean,
  constraint pk_alternativa primary key (id))
;

create table pergunta (
  id                        bigint not null,
  nikname                   varchar(255),
  pergunta                  varchar(255),
  constraint uq_pergunta_nikname unique (nikname),
  constraint pk_pergunta primary key (id))
;

create table resposta (
  pergunta_id               bigint not null,
  alternativa_id            bigint not null,
  nickname_user             varchar(255) not null)
;

create sequence alternativa_seq;

create sequence pergunta_seq;

create sequence resposta_seq;

alter table alternativa add constraint fk_alternativa_pergunta_1 foreign key (pergunta_id) references pergunta (id) on delete restrict on update restrict;
create index ix_alternativa_pergunta_1 on alternativa (pergunta_id);
alter table resposta add constraint fk_resposta_pergunta_2 foreign key (pergunta_id) references pergunta (id) on delete restrict on update restrict;
create index ix_resposta_pergunta_2 on resposta (pergunta_id);
alter table resposta add constraint fk_resposta_alternativa_3 foreign key (alternativa_id) references alternativa (id) on delete restrict on update restrict;
create index ix_resposta_alternativa_3 on resposta (alternativa_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists alternativa;

drop table if exists pergunta;

drop table if exists resposta;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists alternativa_seq;

drop sequence if exists pergunta_seq;

drop sequence if exists resposta_seq;

