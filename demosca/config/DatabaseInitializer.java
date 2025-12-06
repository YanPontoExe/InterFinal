// package com.inter.demosca.config;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import org.apache.ibatis.jdbc.ScriptRunner;
// import org.springframework.core.annotation.Order;

// import javax.sql.DataSource;
// import java.sql.Connection;
// import java.io.InputStreamReader;

// @Order(1)
// @Component
// public class DatabaseInitializer implements CommandLineRunner {

//     private final DataSource dataSource;

//     public DatabaseInitializer(DataSource dataSource) {
//         this.dataSource = dataSource;
//     }

//         @Override
//     public void run(String... args) throws Exception {

//             System.out.println(">>> RUNNER EXECUTADO <<<");

//         try (Connection conn = dataSource.getConnection()) {

//             ScriptRunner runner = new ScriptRunner(conn);

//             var stream = getClass().getClassLoader().getResourceAsStream("init.sql");

//             if (stream == null) {
//                 System.out.println("❌ init.sql NÃO encontrado no classpath!");
//                 return;
//             }

//             System.out.println("✔ init.sql encontrado!");

//             runner.runScript(new InputStreamReader(stream));

//             System.out.println("✔ init.sql executado com sucesso!");
//         }
//     }
// }
