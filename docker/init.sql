-- Script de inicialização do banco de dados
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf BIGINT NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    cidade VARCHAR(100) NOT NULL
);

-- Inserir alguns dados de exemplo (opcional)
INSERT INTO usuarios (nome, cpf, data_nascimento, cidade) VALUES
('João Silva', 12345678901, '1990-01-15', 'São Paulo'),
('Maria Santos', 98765432109, '1985-03-20', 'Rio de Janeiro')
ON CONFLICT (cpf) DO NOTHING;