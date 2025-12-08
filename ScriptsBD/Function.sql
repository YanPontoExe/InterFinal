--FUNCTIONS--

-- Garanta que esta função exista e aceite o parâmetro @idMaterial
ALTER FUNCTION fn_relatorioMovimentacaoMaterial --Retorna um relatório de movimentações
(
    @idMaterial INT -- Aceita um parâmetro
)
RETURNS TABLE
AS
RETURN
(
    SELECT
        m.id_movimentacao, --Identificador único da movimentação
        m.quantidade, --Quantidade movimentada
        mat.descricao AS nome_material, --Nome do material associado à movimentação
        u.username AS usuario, --Retorna o nome do usuário associado à movimentação
        case m.tipo_movimentacao -- Retorna o tipo de movimentação
            when 1 then 'Entrada'
            when 2 then 'Saída'
            else 'Não especificado'
        end as tipo_movimentacao
    FROM 
        tb_movimentacao m
    INNER JOIN 
        tb_material mat ON mat.id_material = m.cod_material
    LEFT JOIN 
        tb_usuario u ON m.cod_usuario = u.id_usuario 
    WHERE
        (@idMaterial IS NULL OR m.cod_material = @idMaterial) -- O filtro é aplicado aqui
);
select * from fn_relatorioMovimentacaoMaterial(NULL); -- Chama a função sem filtro
--PROCEDURE

--Atualiza o status de um funcionário com base no ID fornecido--
CREATE OR ALTER PROCEDURE sp_atualizarStatusFuncionario
    @id INT,
    @novoStatus INT -- 1 Ativo, 2 Férias, 3 Licença, 4 Desligado
AS
BEGIN
    UPDATE tb_funcionario
    SET status = @novoStatus
    WHERE id_funcionario = @id;
END

EXEC sp_atualizarStatusFuncionario
    @id = 12,
    @novoStatus = 3; -- Licença

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
