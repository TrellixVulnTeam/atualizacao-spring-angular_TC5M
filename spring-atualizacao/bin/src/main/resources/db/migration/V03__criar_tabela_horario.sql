CREATE TABLE horario (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	
		codigo_paciente BIGINT NOT NULL,
		codigo_medicamento BIGINT NOT NULL,
		
		cafe_manha VARCHAR(50) DEFAULT '',
		almoco VARCHAR(50) DEFAULT '',
		janta VARCHAR(50) DEFAULT '',
		hora_dormir VARCHAR(50) DEFAULT '',
		importante VARCHAR(50) DEFAULT '',
		descricao VARCHAR(50) NOT NULL,		

	FOREIGN KEY (codigo_paciente) REFERENCES paciente (codigo),
	FOREIGN KEY (codigo_medicamento) REFERENCES medicamento (codigo)
);

INSERT INTO horario (codigo_paciente, codigo_medicamento, cafe_manha, descricao) VALUES (1, 6, 'ANTES', 'beba antes teste');
INSERT INTO horario (codigo_paciente, codigo_medicamento, descricao) VALUES (2, 68, 'teste modo uso');
INSERT INTO horario (codigo_paciente, codigo_medicamento, janta, descricao) VALUES (1, 78, 'DEPOIS', 'TESTE 2');