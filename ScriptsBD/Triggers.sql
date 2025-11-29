create trigger tg_AttEstoque
on tb_saida
instead of insert
as
begin
	update tb_estoque as e set e.qtd_atual = e.qtd_atual - 
end

select * from tb_saida