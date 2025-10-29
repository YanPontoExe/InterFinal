eitities (campos)	= DTO

repository (interfaces)	= DAO - chama as entities

services (repositories) = chama as repositories do tipo repository (requer ArgConstructors)

controller (o fim)



 
**MODELO ESQUELTO**

Setor 
    int id
    String Descricao

fornecedor 
    int id_fornecedor
    String nome_fornecedor
    String cnpj


funcionário
    int id_funcionario
    int cod_setor
    String nome
    String turno //Manhã, Tarde, Noite
    LocalDate dataContratacao
    int Status
    
            usuário
                int id_user
                String user 
                String senha
                boolean status
                boolean tipo_usuario

material
    int id_material
    String descrição
    int cod_marca
    int cod_fornecedor
    LocalDateTime dataCadastro
    int status

marcas
    int id_marca
    String nome_marca
    String pais_origem
    String descricao
    LocalDateTime dataCadastro
    boolean status

movimentação
    int id_movimentacao
    int cod_material
    int quantidade
    int cod_usuario

        entrada
            int cod_fornecedor
            String notaFiscal
            LocalDate dataEntrada

        saída
            cod_funcionario
            String motivo //venda, transferência, devolução, etc.
            LocalDate dataSaida 

movimento_material (entidade associativa entre - movimentação e material)
    int qtd
    int cod_material
    int cod_movimentacao
    (chave primaria composta = cod_material + cod_movimentacao)
