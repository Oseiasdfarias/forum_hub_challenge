alter table topicos
add column autor_id bigint not null,
add constraint fk_autor
foreign key (autor_id) references autores(id);
