create trigger tg_AttEstoqueSaida
on tb_saida
instead of insert
as
begin
	update tb_estoque set qtd_atual = qtd_atual - (	
		select m.quantidade
		from tb_movimentacao as m, inserted as i, tb_saida as s
		where @@IDENTITY = m.id_movimentacao  --pegando a ultima movimentação cadastrada
		and i.data_saida =  s.data_saida --identificando que é uma movimentação de saida
	)
end
go


create trigger tg_AttEstoqueEntrada
on tb_entrada
instead of insert
as
begin
	update tb_estoque set qtd_atual = qtd_atual + (	
		select m.quantidade
		from tb_movimentacao as m, inserted as i, tb_entrada as e
		where @@IDENTITY = m.id_movimentacao  --pegando a ultima movimentação cadastrada
		and i.data_entrada =  e.data_entrada --identificando que é uma movimentação de saida
	)
end
go



select * from tb_entrada
select * from tb_saida



