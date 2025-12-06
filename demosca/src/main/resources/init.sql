
--FUNCTIONS--

--Contador do total de movimentacoes realizadas por Id do material
CREATE FUNCTION fn_totalMovimentacoesMaterial(@id_material INT)
RETURNS INT
AS
BEGIN
    DECLARE @total INT;

    SELECT @total = COUNT(*) 
    FROM tb_movimentacao
    WHERE cod_material = @id_material;

    RETURN @total;
END;

--Retorna todas as saidas junto do codigo do material e qtd
Create FUNCTION fn_listarSaidas()
RETURNS TABLE
AS
RETURN
(
    SELECT 
        mat.descricao,
        m.quantidade,
        s.motivo,
        s.data_saida,
        s.id_movimentacao,
        m.cod_material,
        s.cod_funcionario
    FROM tb_saida AS s
    INNER JOIN tb_movimentacao AS m
        ON s.id_movimentacao = m.id_movimentacao
    LEFT JOIN  tb_material AS mat
    	ON m.cod_material = mat.id_material
);

--Retorna todas as entradas junto do codigo do material e qtd
CREATE FUNCTION fn_listarEntradas()
RETURNS TABLE
AS
RETURN
(
    SELECT 
        mat.descricao,
        m.quantidade,
        e.data_entrada,
    	e.id_movimentacao,
        m.cod_material,
        e.cod_fornecedor,
        e.nota_fiscal
    FROM tb_entrada AS e
    INNER JOIN tb_movimentacao AS m
        ON e.id_movimentacao = m.id_movimentacao
    LEFT JOIN  tb_material AS mat
    	ON m.cod_material = mat.id_material
);

--PROCEDURE

--Gera um relatório completo de todas as movimentações (entradas e saídas) realizadas sobre um Id de material específico
create PROCEDURE sp_relatorioMovimentacaoMaterial
(
    @idMaterial INT = NULL
)
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        BEGIN TRANSACTION;

        SELECT
            m.id_movimentacao, 
            m.quantidade,
            mat.descricao AS nome_material,
            u.username as usuario
        FROM tb_movimentacao m
        INNER JOIN tb_material mat     ON mat.id_material = m.cod_material
        Left join tb_usuario u		   ON m.cod_usuario = u.id_usuario 
        WHERE
            (@idMaterial IS NULL OR m.cod_material = @idMaterial)

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        THROW;
    END CATCH
END

--VIEW--

--Chama todos os materiais cadastrados com suas respectivas marcas e fornecedores--
CREATE VIEW vw_movimentacao_estoque AS
SELECT 
	mat.descricao as material,
	mat.marca,
	mat.data_cadastro as data_cadastro_material,
	mat.status,
	m.nome_marca,
	m.status as status_marca,
	m.pais_origem,
	m.descricao_marca,
	m.data_cadastro as data_cadastro_marca,
	f.nome_fornecedor,
	f.cnpj
	from tb_material mat
	left join tb_marca m ON mat.marca = m.nome_marca
	left join tb_fornecedor f ON mat.cod_fornecedor = f.id_fornecedor;

--TRIGGERS--

CREATE TRIGGER tr_ValidaEstoque_Saida
ON tb_saida
INSTEAD OF INSERT
AS
BEGIN
    
    DECLARE @EstoqueInsuficiente BIT = 0;
    
    
    CREATE TABLE #SaidasAValidar ( --# = tabela temporária
        id_movimentacao INT,
        cod_funcionario INT,
        motivo VARCHAR(255),
        data_saida DATETIME,
        cod_material INT,
        quantidade_saida INT,
        SaldoAtual INT
    );

    -- inserindo dados da nova saída na tabela temporária e calculando o saldo_atual
    INSERT INTO #SaidasAValidar (id_movimentacao, cod_funcionario, motivo, data_saida, cod_material, quantidade_saida, SaldoAtual)
    SELECT 
        I.id_movimentacao,
        I.cod_funcionario,
        I.motivo,
        I.data_saida,
        M.cod_material,
        M.quantidade AS quantidade_saida,
        (
            SELECT 
                SUM(CASE WHEN E.id_movimentacao IS NOT NULL THEN M_Hist.quantidade ELSE 0 END) - 
                SUM(CASE WHEN S.id_movimentacao IS NOT NULL THEN M_Hist.quantidade ELSE 0 END)   
            FROM tb_movimentacao M_Hist
            LEFT JOIN tb_entrada E ON M_Hist.id_movimentacao = E.id_movimentacao
            LEFT JOIN tb_saida S ON M_Hist.id_movimentacao = S.id_movimentacao
            WHERE M_Hist.cod_material = M.cod_material
        ) AS SaldoAtual
    FROM INSERTED I
    INNER JOIN tb_movimentacao M ON I.id_movimentacao = M.id_movimentacao;


    IF EXISTS (
        SELECT 1 
        FROM #SaidasAValidar
        WHERE SaldoAtual < quantidade_saida
    )
    BEGIN
        
        SET @EstoqueInsuficiente = 1;

        ROLLBACK TRANSACTION;
    END

    
    IF @EstoqueInsuficiente = 0
    BEGIN
        INSERT INTO tb_saida (id_movimentacao, cod_funcionario, motivo, data_saida)
        SELECT id_movimentacao, cod_funcionario, motivo, data_saida
        FROM INSERTED;
    END

    DROP TABLE #SaidasAValidar;
END;



CREATE TRIGGER trg_funcionario_data_contratacao
ON tb_funcionario
AFTER INSERT
AS
BEGIN
    
    UPDATE T
    SET T.data_contratacao = GETDATE() 
    FROM tb_funcionario AS T
    INNER JOIN Inserted AS I ON T.id_funcionario = I.id_funcionario;

END;


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


INSERT INTO tb_material (descricao, marca, cod_fornecedor, data_cadastro, status) VALUES
('Parafuso M8', 'WAP', 1, GETDATE(), 1),
('Resina ABS Branca', 'PolyChem', 2, GETDATE(), 1),
('Tinta Epóxi Azul', 'Pintalux', 3, GETDATE(), 1),
('Chapa de Aço 10mm', 'CSN', 4, GETDATE(), 1),
('Alicate de Corte', 'ToolMaster', 5, GETDATE(), 1),
('Embalagem Plástica P', 'Plastiko', 6, GETDATE(), 1),
('Sensor de Proximidade', 'SensoTech', 7, GETDATE(), 1),
('Fita Adesiva Industrial', 'TapeFix', 8, GETDATE(), 1),
('Broca 5mm HSS', 'StarDrill', 9, GETDATE(), 1),
('Motor Elétrico 5CV', 'WEG', 10, GETDATE(), 1);


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
INSERT INTO tb_movimentacao (cod_material, quantidade, cod_usuario) VALUES
-- Entradas (cod_material 1 a 10)
(1, 500, 1),
(2, 100, 1),
(3, 200, 2),
(4, 5, 1),
(5, 10, 3),
(6, 50, 1),
(7, 120, 2),
(8, 300, 1),
(9, 45, 3),
(10, 1, 1),
-- Saídas (cod_material 1 a 10)
(1, 5, 4),
(2, 10, 5),
(3, 5, 6),
(4, 1, 4),
(5, 2, 5),
(6, 5, 7),
(7, 3, 8),
(8, 10, 4),
(9, 1, 6),
(10, 1, 10);


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

