
-- Trigger para atualizar o tipo de movimentação ao inserir uma nova entrada
CREATE TRIGGER tr_Entrada_Tipo
ON tb_entrada
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- Atualiza o tipo na tabela tb_movimentacao para 1 (Entrada)
    UPDATE M
    SET tipo_movimentacao = 1 
    FROM tb_movimentacao AS M
    INNER JOIN Inserted AS I ON M.id_movimentacao = I.id_movimentacao;

END;

-- Trigger para atualizar o tipo de movimentação ao inserir uma nova saída
CREATE TRIGGER tr_Saida_Tipo
ON tb_saida
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- Atualiza o tipo na tabela tb_movimentacao para 2 (Saída)
    UPDATE M
    SET tipo_movimentacao = 2 
    FROM tb_movimentacao AS M
    INNER JOIN Inserted AS I ON M.id_movimentacao = I.id_movimentacao;

END;


-- Trigger para atualizar a data de contratação ao inserir um novo funcionário
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

-- Trigger para validar estoque antes de registrar uma saída
CREATE TRIGGER tr_ValidaEstoque_Saida
ON tb_saida
INSTEAD OF INSERT -- Trigger que intercepta a inserção na tabela tb_saida
AS
BEGIN
    SET NOCOUNT ON;
    
    -- 1. Cria uma tabela temporária para coletar os dados necessários e o saldo atual
    CREATE TABLE #ValidacaoSaida (
        id_movimentacao INT PRIMARY KEY,
        cod_material INT,
        quantidade_saida INT,
        estoque_atual INT, -- Quantidade de tb_material
        cod_funcionario INT,
        motivo VARCHAR(255),
        data_saida DATETIME
    );

    -- 2. Popula a tabela temporária, obtendo a quantidade a sair (tb_movimentacao) e o estoque atual (tb_material)
    INSERT INTO #ValidacaoSaida (
        id_movimentacao, cod_material, quantidade_saida, estoque_atual, cod_funcionario, motivo, data_saida
    )
    SELECT
        I.id_movimentacao,
        M.cod_material,
        M.quantidade AS quantidade_saida,
        MAT.quantidade AS estoque_atual, -- Pega o saldo da tabela tb_material
        I.cod_funcionario,
        I.motivo,
        I.data_saida
    FROM INSERTED I
    INNER JOIN tb_movimentacao M ON I.id_movimentacao = M.id_movimentacao
    INNER JOIN tb_material MAT ON M.cod_material = MAT.id_material;

    -- 3. Validação: Checa se há algum registro onde a saída excede o estoque atual
    IF EXISTS (
        SELECT 1 
        FROM #ValidacaoSaida
        WHERE estoque_atual < quantidade_saida
    )
    BEGIN
        -- Aborta a transação se houver estoque insuficiente
        ROLLBACK TRANSACTION;
        
        -- Lança um erro para notificar o sistema que tentou a inserção
        THROW 50001, 'A saída não pode ser registrada. Estoque insuficiente para um ou mais materiais.', 1;
        
        RETURN; -- Sai do trigger
    END

    -- 4. Se a validação passou (Estoque Suficiente): Atualização de Estoque e Inserção
    
    -- 4.1. Subtrai a quantidade da tb_material
    UPDATE MAT
    SET MAT.quantidade = MAT.quantidade - V.quantidade_saida
    FROM tb_material MAT
    INNER JOIN #ValidacaoSaida V ON MAT.id_material = V.cod_material;
    
    -- 4.2. Insere os dados na tabela tb_saida (substituindo o INSERT original)
    INSERT INTO tb_saida (id_movimentacao, cod_funcionario, motivo, data_saida)
    SELECT id_movimentacao, cod_funcionario, motivo, data_saida
    FROM #ValidacaoSaida;
    
    -- 5. Limpeza
    DROP TABLE #ValidacaoSaida;
END;
GO