ALTER TABLE usuario
MODIFY codigo CHAR(7) NOT NULL,
MODIFY nome VARCHAR(50) NOT NULL,
MODIFY email VARCHAR(50) NOT NULL UNIQUE,
MODIFY telefone VARCHAR(20);