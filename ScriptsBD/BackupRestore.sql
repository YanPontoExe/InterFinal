backup realizado via terminal docker --->

--entrar no container
docker exec -it interD bash --interD = nome do container

--realizar o backup
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P 'interSQL101' -C -Q "BACKUP DATABASE interD TO DISK = '/var/opt/mssql/backup/bkp.bak'"
--S = 'servidor local'
--U = 'usuario sa'
--P = 'senha do sa'
--C = 'usar conexao criptografada'
--Q = 'query a ser executada'
--To DISK = 'caminho do arquivo de backup dentro do container'

--sair do container
exit

--puxar o arquivo de backup para a máquina local
docker cp interD:/var/opt/mssql/backup/bkp.bak C:\Users\henrique.martins\Documents\backupsql\bkp.bak
--cp = 'comando de copia'


--restore:
--Realizar o processo inverso para restaurar o backup

--acessao container
docker exec -it interD bash

--Setar bd para single user com rollback imediato
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P 'interSQL101' -C -Q "ALTER DATABASE interD SET SINGLE_USER WITH ROLLBACK IMMEDIATE"

--puxar o arquivo de backup para o container
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P 'interSQL101' -C -Q "RESTORE DATABASE interD FROM DISK = '/var/opt/mssql/backup/bkp.bak' WITH REPLACE"

--setar bd para multi user
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P 'interSQL101' -C -Q "ALTER DATABASE interD SET MULTI_USER"