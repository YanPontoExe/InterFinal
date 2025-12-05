package com.inter.demosca.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.demosca.Services.BackupService;

@RestController
@RequestMapping("/api/backup")
public class BackupController {

    @Autowired
    private BackupService backupService;

    @PostMapping("/executar")
    public Response executarBackup() {
        String mensagem = backupService.executarBackup();
        return new Response(mensagem);
    }

    @PostMapping("/restaurar")
    public Response restaurarBackup() {
        String mensagem = backupService.restaurarBackup();
        return new Response(mensagem);
    }

    // classe interna para retorno JSON
    record Response(String mensagem) {}
}