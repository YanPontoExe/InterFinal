# EstoKar - API Backend

> Este é o repositório principal do Backend da aplicação EstoKar. Ele hospeda a API RESTful responsável por toda a lógica de negócios, manipulação de dados e autenticação.

## 🌟 Visão Geral

Esta API foi construída utilizando Java e segue o padrão arquitetural RESTful. Ela serve como o coração do sistema, fornecendo *endpoints* para o Frontend e/ou para aplicações externas.

### 🧱 Tecnologias Chave

* **Linguagem: Java
* **Framework: Spring Boot
* **Banco de Dados: Microsoft SQL Server
* **Autenticação:** JWT 

---

## 🛠️ Configuração e Instalação

Siga os passos para configurar o ambiente de desenvolvimento.

### Pré-requisitos

* Node.js (Versão Mínima: [v25.2.1,])
* Java (Versão Mínima: [v17])
* **Acesso a uma instância de SQL Server**

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/YanPontoExe/InterFinal
    ```

2.  **Acesse o diretório:**
    ```bash
    cd /InterFinal
    ```

3.  **Configuração de Ambiente (.env):**
    * Crie um arquivo `.env` na raiz do projeto.
    * Preencha as seguintes variáveis (exemplos):
        ```env
        # Configurações do Banco de Dados
        DB_HOST=[host_do_seu_db]
        DB_USER=sa
        DB_PASSWORD=@A123456
        DB_NAME=testeInter1

        # Configurações de Segurança
        SECRET_KEY=[sua_chave_secreta_aqui]
        JWT_EXPIRATION_DAYS=7
        ```

4.  **Inicie o Servidor:**

Acesse o diretório 

    .../InterFinal/demosca/src/main/java/com/inter/demosca 
    
abra o arquivo 

    DemoscaApplication.java 

 inicialize o servidor com o comando `run` acima da classe `main`.
   
O servidor estará acessível em `http://localhost:8080`.

---
