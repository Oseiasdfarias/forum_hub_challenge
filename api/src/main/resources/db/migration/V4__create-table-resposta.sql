create table resposta(

    id bigint not null auto_increment,
    mensagem varchar(255) not null,
    dataCriacao datetime not null,
    solucao tinyint not null,
    topico_id bigint,
    autor_id bigint,

    primary key(id),
    foreign key (topico_id) references topicos(id),
    foreign key (autor_id) references autores(id)
);