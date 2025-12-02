sCREATE FUNCTION fn_totalMovimentacoesMaterial(@id_material INT)
RETURNS INT
AS
BEGIN
    DECLARE @total INT;

    SELECT @total = COUNT(*)
    FROM tb_movimentacao
    WHERE cod_material = @id_material;

    RETURN @total;
END;

SELECT dbo.fn_totalMovimentacoesMaterial(:idMaterial)