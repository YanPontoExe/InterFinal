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