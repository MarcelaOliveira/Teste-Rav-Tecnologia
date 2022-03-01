DROP SCHEMA IF EXISTS funcionarios;
CREATE SCHEMA IF NOT EXISTS funcionarios;
-- Modelo Físico
CREATE TABLE funcionarios.funcionario(
    id INT(11) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    endereco VARCHAR(100) NOT NULL,
    telefone VARCHAR(100) NOT NULL
);

