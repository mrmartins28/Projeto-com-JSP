alter table model_login add id serial primary key;

select * from model_login;

alter table model_login add nome character varying(300);

alter table model_login add email character varying(300);



update model_login set email = '';

alter table model_login alter column email set no1t null;

alter table model_login add column usuario_id bigint not null default 1;

alter table model_login add constraint model_login_pkey primary key (id);

alter table model_login add constraint usuario_fk foreign key (usuario_id) references model_login(id);

alter table model_login add column perfil character varying(200);

update model_login set useradmin=true where login='admin2';
