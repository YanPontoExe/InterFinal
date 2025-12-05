package com.seuprojeto.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.apache.ibatis.jdbc.ScriptRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.io.FileReader;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final DataSource dataSource;

    public DatabaseInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            ScriptRunner runner = new ScriptRunner(conn);

            runner.runScript(new FileReader("src/main/resources/init.sql"));

            System.out.println("\n▶ init.sql executado com sucesso após criação das tabelas.\n");
        }
    }
}
