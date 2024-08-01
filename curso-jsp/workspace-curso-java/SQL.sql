alter table model_login add id serial primary key;

select * from model_login;

alter table model_login add nome character varying(300);

alter table model_login add email character varying(300);



update model_login set email = '';

alter table model_login alter column email set not null;
