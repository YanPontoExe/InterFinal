backup:
--entrar no container
docker exec -it interD bash

--realizar o backup
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P 'interSQL101' -C -Q "BACKUP DATABASE interD TO DISK = '/var/opt/mssql/backup/bkp.bak'"

--sair do container
exit

--puxar o arquivo de backup para a máquina local
docker cp interD:/var/opt/mssql/backup/bkp.bak C:\Users\henrique.martins\Documents\backupsql\bkp.bak

--restore:

--acessao container
docker exec -it interD bash

--Setar bd para single user
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P 'interSQL101' -C -Q "ALTER DATABASE interD SET SINGLE_USER WITH ROLLBACK IMMEDIATE"

--puxar o arquivo de backup para o container
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P 'interSQL101' -C -Q "RESTORE DATABASE interD FROM DISK = '/var/opt/mssql/backup/bkp.bak' WITH REPLACE"

--setar bd para multi user
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P 'interSQL101' -C -Q "ALTER DATABASE interD SET MULTI_USER"