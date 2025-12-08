--Inserção de dados em todas as tabelas presentes no sistema

INSERT INTO tb_setor (descricao) VALUES
('Produção A'),
('Produção B'),
('Manutenção'),
('Almoxarifado'),
('Expedição'),
('Engenharia'),
('P&D'),
('Controle de Qualidade'),
('Administrativo'),
('Vendas');

INSERT INTO tb_funcionario (nome_funcionario, setor, turno, data_contratacao, status) VALUES
('Ana Paula Costa', 'Produção A', 'Manhã', GETDATE(), 1),
('Bruno Souza Lima', 'Produção B', 'Tarde', GETDATE(), 1),
('Carla Moraes', 'Manutenção', 'Noite', GETDATE(), 1),
('Davi Ramos', 'Almoxarifado', 'Manhã', GETDATE(), 1),
('Eduardo Neves', 'Almoxarifado', 'Tarde', GETDATE(), 1),
('Fernanda Melo', 'Almoxarifado', 'Noite', GETDATE(), 1),
('Gabriel Santos', 'Produção A', 'Tarde', GETDATE(), 1),
('Helena Braga', 'Produção B', 'Manhã', GETDATE(), 1),
('Igor Freire', 'Manutenção', 'Manhã', DATEADD(YEAR, -5, GETDATE()), 2), -- Inativo
('Júlia Pires', 'Expedição', 'Manhã', GETDATE(), 1);


INSERT INTO tb_usuario (username, password) VALUES
('davi.almox', 'senha123'), -- Adm
('eduardo.n', 'senha123'),
('fernanda.m', 'senha123'),
('ana.paula', 'senha123'),
('bruno.s', 'senha123'),
('carla.m', 'senha123'),
('gabriel.s', 'senha123'),
('helena.b', 'senha123'),
('igor.f', 'senha123'), -- Inativo
('julia.p', 'senha123'); -- Adm


INSERT INTO tb_fornecedor (nome_fornecedor, cnpj) VALUES
('Alpha Componentes', '11.111.111/0001-11'),
('Beta Plásticos S.A.', '22.222.222/0001-22'),
('Gama Químicos', '33.333.333/0001-33'),
('Delta Metais', '44.444.444/0001-44'),
('Epsilon Tools', '55.555.555/0001-55'),
('Zeta Embalagens', '66.666.666/0001-66'),
('Iota Eletrônicos', '77.777.777/0001-77'),
('Kappa Adesivos', '88.888.888/0001-88'),
('Lambda Ferramentas', '99.999.999/0001-99'),
('Omega Máquinas', '00.000.000/0001-00');


INSERT INTO tb_material (descricao, marca, quantidade, cod_fornecedor, data_cadastro, status) VALUES
('Parafuso M8', 'WAP', 20, 1, GETDATE(), 1),
('Resina ABS Branca', 'PolyChem', 233, 2, GETDATE(), 1),
('Tinta Epóxi Azul', 'Pintalux', 3, 324, GETDATE(), 1),
('Chapa de Aço 10mm', 'CSN', 4, 40, GETDATE(), 1),
('Alicate de Corte', 'ToolMaster', 5, 50, GETDATE(), 1),
('Embalagem Plástica P', 'Plastiko', 6, 75, GETDATE(), 1),
('Sensor de Proximidade', 'SensoTech', 7, 45, GETDATE(), 1),
('Fita Adesiva Industrial', 'TapeFix', 8, 10, GETDATE(), 1),
('Broca 5mm HSS', 'StarDrill', 9, 40, GETDATE(), 1),
('Motor Elétrico 5CV', 'WEG', 10, 50, GETDATE(), 1);



INSERT INTO tb_marca (nome_marca, pais_origem, descricao_marca, data_cadastro, status) VALUES
('WAP', 'Brasil', 'Parafusos e Fixadores', GETDATE(), 1),
('PolyChem', 'EUA', 'Resinas e Polímeros', GETDATE(), 1),
('Pintalux', 'Alemanha', 'Tintas e Revestimentos', GETDATE(), 1),
('CSN', 'Brasil', 'Siderurgia e Aços', GETDATE(), 1),
('ToolMaster', 'China', 'Ferramentas Manuais', GETDATE(), 1),
('Plastiko', 'Brasil', 'Embalagens plásticas', GETDATE(), 1),
('SensoTech', 'Japão', 'Componentes Eletrônicos', GETDATE(), 1),
('TapeFix', 'Canadá', 'Fitas e Adesivos', GETDATE(), 1),
('StarDrill', 'EUA', 'Brocas e Usinagem', GETDATE(), 1),
('WEG', 'Brasil', 'Motores Elétricos', GETDATE(), 1);


-- 10 Entradas (IDs 1-10) e 10 Saídas (IDs 11-20)
INSERT INTO tb_movimentacao (cod_material, quantidade, cod_usuario, tipo_movimentacao) VALUES
-- Entradas (cod_material 1 a 10)
(1, 500, 1, 1),
(2, 100, 1, 1),
(3, 200, 2, 1),
(4, 5, 1, 1),
(5, 10, 3, 1),
(6, 50, 1, 1),
(7, 120, 2, 1),
(8, 300, 1, 1),
(9, 45, 3, 1),
(10, 1, 1, 1),
-- Saídas (cod_material 1 a 10)
(1, 5, 4, 2),
(2, 10, 5, 2),
(3, 5, 6, 2),
(4, 1, 4, 2),
(5, 2, 5, 2),
(6, 5, 7, 2),
(7, 3, 8, 2),
(8, 10, 4, 2),
(9, 1, 6, 2),
(10, 1, 10, 2);

select * from tb_material

-- id_movimentacao de 1 a 10
INSERT INTO tb_entrada (id_movimentacao, cod_fornecedor, nota_fiscal, data_entrada) VALUES
(1, 1, '0001-A', '2025-11-20 10:00:00'),
(2, 2, '0002-B', '2025-11-20 11:30:00'),
(3, 3, '0003-C', '2025-11-21 08:00:00'),
(4, 4, '0004-D', '2025-11-21 14:00:00'),
(5, 5, '0005-E', '2025-11-22 09:15:00'),
(6, 6, '0006-F', '2025-11-22 15:45:00'),
(7, 7, '0007-G', '2025-11-23 10:20:00'),
(8, 8, '0008-H', '2025-11-23 16:00:00'),
(9, 9, '0009-I', '2025-11-24 09:00:00'),
(10, 10, '0010-J', '2025-11-24 13:00:00');


-- id_movimentacao de 11 a 20
INSERT INTO tb_saida (id_movimentacao, cod_funcionario, motivo, data_saida) VALUES
(1, 1, 'Consumo - Produção A', '2025-11-25 08:30:00'),
(12, 2, 'Consumo - Produção B', '2025-11-25 10:00:00'),
(13, 3, 'Manutenção Corretiva', '2025-11-25 14:00:00'),
(14, 1, 'Consumo - Produção A', '2025-11-26 09:10:00'),
(15, 2, 'Consumo - Produção B', '2025-11-26 11:45:00'),
(16, 7, 'Transferência para filial', '2025-11-26 15:00:00'),
(17, 8, 'Consumo - Produção B', '2025-11-27 08:00:00'),
(18, 1, 'Consumo - Produção A', '2025-11-27 10:30:00'),
(19, 3, 'Manutenção Preventiva', '2025-11-27 14:00:00'),
(20, 10, 'Devolução ao Fornecedor', '2025-11-28 09:00:00');

SELECT * FROM tb_setor;
SELECT * FROM tb_funcionario;
SELECT * FROM tb_usuario;
SELECT * FROM tb_fornecedor;
SELECT * FROM tb_material;
SELECT * FROM tb_marca;
SELECT * FROM tb_movimentacao;
SELECT * FROM tb_entrada;
SELECT * FROM tb_saida;