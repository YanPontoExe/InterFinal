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

/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P 'interSQL101' -C -Q "RESTORE DATABASE interD FROM DISK = '/var/opt/mssql/backup/bkp.bak' WITH REPLACE"

--Restore
ALTER DATABASE interD 
SET SINGLE_USER WITH ROLLBACK IMMEDIATE;

RESTORE DATABASE interD
FROM DISK = 'C:\backups\backup_interD.bak'
WITH REPLACE;

ALTER DATABASE interD 
SET MULTI_USER;
