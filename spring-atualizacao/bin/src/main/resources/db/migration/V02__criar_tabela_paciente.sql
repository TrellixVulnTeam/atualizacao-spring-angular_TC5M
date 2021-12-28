CREATE TABLE paciente (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	email VARCHAR(50) NOT NULL
);

insert into paciente (nome, cpf, email) values ('paciente teste 1', '11122233345', 'teste1@teste.com');
insert into paciente (nome, cpf, email) values ('paciente 2', '99922288823', 'teste2@teste.com');