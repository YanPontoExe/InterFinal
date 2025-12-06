--FUNCTIONS--

--FUNCTIONS--
-- Garanta que esta função exista e aceite o parâmetro @idMaterial
ALTER FUNCTION fn_relatorioMovimentacaoMaterial
(
    @idMaterial INT -- Aceita um parâmetro
)
RETURNS TABLE
AS
RETURN
(
    SELECT
        m.id_movimentacao, 
        m.quantidade,
        mat.descricao AS nome_material,
        u.username AS usuario
    FROM 
        tb_movimentacao m
    INNER JOIN 
        tb_material mat ON mat.id_material = m.cod_material
    LEFT JOIN 
        tb_usuario u ON m.cod_usuario = u.id_usuario 
    WHERE
        (@idMaterial IS NULL OR m.cod_material = @idMaterial) -- O filtro é aplicado aqui
);

--PROCEDURE

--Gera um relatório completo de todas as movimentações (entradas e saídas) realizadas sobre um Id de material específico


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
