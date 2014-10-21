# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table alternativa (
  id                        bigint auto_increment not null,
  descricao                 varchar(255),
  alternativa_certa         tinyint(1) default 0,
  pergunta_id               bigint,
  constraint pk_alternativa primary key (id))
;

create table pergunta (
  id                        bigint auto_increment not null,
  nikname                   varchar(255),
  pergunta                  varchar(255),
  constraint uq_pergunta_nikname unique (nikname),
  constraint pk_pergunta primary key (id))
;

create table resposta (
  pergunta_id               bigint,
  alternativa_id            bigint,
  nickname_user             varchar(255),
  constraint uq_resposta_nickname_user unique (nickname_user),
  constraint pk_resposta primary key (pergunta_id, alternativa_id))
;

alter table alternativa add constraint fk_alternativa_pergunta_1 foreign key (pergunta_id) references pergunta (id) on delete restrict on update restrict;
create index ix_alternativa_pergunta_1 on alternativa (pergunta_id);
alter table resposta add constraint fk_resposta_pergunta_2 foreign key (pergunta_id) references pergunta (id) on delete restrict on update restrict;
create index ix_resposta_pergunta_2 on resposta (pergunta_id);
alter table resposta add constraint fk_resposta_alternativa_3 foreign key (alternativa_id) references alternativa (id) on delete restrict on update restrict;
create index ix_resposta_alternativa_3 on resposta (alternativa_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table alternativa;

drop table pergunta;

drop table resposta;

SET FOREIGN_KEY_CHECKS=1;

