create database cliente;
create table clientes(
	id bigint not null auto_increment,
	nome varchar(255),
	email varchar(255),
	endereco varchar(255),
    telefone varchar(20),
	dataCadastro date,
    primary key	(id)
);