CREATE TABLE usuario (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL,
	tipo VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	codigo_usuario BIGINT NOT NULL,
	codigo_permissao BIGINT NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (codigo, nome, email, tipo, senha) values (1, 'Administrador', 'admin@alertamed.com', 'COMUM', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (codigo, nome, email, tipo, senha) values (2, 'Daniel Sanches', 'daniel@alertamed.com', 'COMUM', '$2a$10$SsQyj68jZV7j.XLikDaPVus5OkRbYvHBkOMfJeescUURZbHuGHFZ6');
INSERT INTO usuario (codigo, nome, email, tipo, senha) values (3, 'Eduardo Habib', 'eduardo@alertamed.com', 'COMUM', '$2a$10$qLtKrq8MPwi4CvqECjavBeiCQ79a66NjF4AuwFqyxyOjji3XPANPa');

-- permissoes
INSERT INTO permissao (codigo, descricao) values (1, 'ROLE_CADASTRAR_MEDICAMENTO');
INSERT INTO permissao (codigo, descricao) values (2, 'ROLE_PESQUISAR_MEDICAMENTO');

INSERT INTO permissao (codigo, descricao) values (3, 'ROLE_CADASTRAR_HORARIO');
INSERT INTO permissao (codigo, descricao) values (4, 'ROLE_PESQUISAR_HORARIO');

INSERT INTO permissao (codigo, descricao) values (5, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao (codigo, descricao) values (6, 'ROLE_PESQUISAR_USUARIO');

INSERT INTO permissao (codigo, descricao) values (7, 'ROLE_CADASTRAR_PACIENTE');
INSERT INTO permissao (codigo, descricao) values (8, 'ROLE_PESQUISAR_PACIENTE');

INSERT INTO permissao (codigo, descricao) values (9, 'ROLE_CADASTRAR_PERMISSAO');
INSERT INTO permissao (codigo, descricao) values (10, 'ROLE_PESQUISAR_PERMISSAO');

INSERT INTO permissao (codigo, descricao) values (11, 'ROLE_GERAR_RELATORIO');


-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 7);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 8);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 10);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 11);

-- daniel
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 8);

-- eduardo
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (3, 4);