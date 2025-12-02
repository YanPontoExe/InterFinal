--Trigger da tabela tb_funcionario
--atualiza automaticamente a coluna data_contratacao com a data e hora atual só quando um novo registro é inserido com sucesso

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
