--FUNCTIONS--

--Contador do total de movimentacoes realizadas por Id do material
CREATE FUNCTION fn_totalMovimentacoesMaterial(@id_material INT)
RETURNS INT
AS
BEGIN
    DECLARE @total INT;

    SELECT @total = COUNT(*), 
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
