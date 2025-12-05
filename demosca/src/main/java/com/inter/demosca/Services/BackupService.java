package com.inter.demosca.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BackupService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String DATABASE_NAME = "interD";
    private static final String BACKUP_PATH = "C:\\backups\\backup_interD.bak";

    public String executarBackup() {
        try {
            String sql = "BACKUP DATABASE " + DATABASE_NAME +
                         " TO DISK = '" + BACKUP_PATH + "' " +
                         " WITH INIT";

            jdbcTemplate.execute(sql);
            return "Backup gerado em: " + BACKUP_PATH;

        } catch (Exception e) {
            return "Erro ao gerar backup: " + e.getMessage();
        }
    }

    public String restaurarBackup() {
        try {
            String setSingleUser = "ALTER DATABASE " + DATABASE_NAME +
                                   " SET SINGLE_USER WITH ROLLBACK IMMEDIATE";

            String restoreSql = "RESTORE DATABASE " + DATABASE_NAME +
                                " FROM DISK = '" + BACKUP_PATH + "' WITH REPLACE";

            String setMultiUser = "ALTER DATABASE " + DATABASE_NAME + " SET MULTI_USER";

            jdbcTemplate.execute(setSingleUser);
            jdbcTemplate.execute(restoreSql);
            jdbcTemplate.execute(setMultiUser);

            return "Restore concluído usando o arquivo: " + BACKUP_PATH;

        } catch (Exception e) {
            return "Erro ao restaurar backup: " + e.getMessage();
        }
    }
}
