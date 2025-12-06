--TRIGGERS--

--Dispara a atualização da entrada 
CREATE TRIGGER tg_AttEstoqueSaida
ON tb_saida
AFTER INSERT
AS
BEGIN
    -- Atualizar o estoque com base na movimentação
    UPDATE e
    SET e.qtd_atual = e.qtd_atual - m.quantidade
    FROM tb_estoque e
    --mapeia o id da movimentacao informado para caracterizar que será saida
    INNER JOIN tb_movimentacao m ON e.id_material = m.cod_material
    INNER JOIN inserted i ON i.id_movimentacao = m.id_movimentacao;
END;


CREATE TRIGGER tg_AttEstoqueEntrada
ON tb_entrada
AFTER INSERT
AS
BEGIN 
    -- Atualizar o estoque com base na movimentação
    UPDATE e
    SET e.qtd_atual = e.qtd_atual + m.quantidade
    FROM tb_estoque e
    --mapeia o id da movimentacao informado para caracterizar que será entrada
    INNER JOIN tb_movimentacao m ON e.id_material = m.cod_material
    INNER JOIN inserted i ON i.id_movimentacao = m.id_movimentacao;
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