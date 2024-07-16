create table topicos(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensagem varchar(100) not null unique,
    data datetime not null,
    status tinyint not null,
    curso varchar(100) not null,

    primary key(id)
--    constraint fk_topicos_autor_id foreign key(autor_id) references autores(id)

);