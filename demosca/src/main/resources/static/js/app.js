//funções para navegação entre páginas, pelos button's
function entrar_materiais(){
    window.location.href = "/access-material";
}

function return_index(){
    window.location.href = "/";
}

function entrar_usuarios(){
    window.location.href = "/access-user";
}

function entrar_fornecedores(){
    window.location.href = "/access-fornecedor";
}

function entrar_marcas(){
    window.location.href = "/access-marca";
}

function exit(){
    window.location.href = "/auth";
}




//funcção para cadastrar login do funcionario
async function cadastrar_login(){
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    try {
        let response = await fetch("/Usuario", {
            method: "POST",
            headers: {  
                        "usuario:": username,
                        "senha:": password, 
                        "cod_funcionario:":1,
                        "status:": 1,
                        "tipo_usuario:": 1
                     }
        });
    } catch (error) {
        alert("Erro ao cadastrar login: " + error);
    }
}

//tentativa de validação de usuario
async function validar_login(){
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    try {
        let response = await fetch("/Usuario", {
            method: "GET"
        });

        let data = await response.json();

        let user = data[0].user;
        let senha = data[0].senha;

    if(password == senha && username == user){
        window.location.href = "index.html";
    } else {
        alert("Usuário ou senha incorretos!");
    }
    } catch (error) {
        
    }
} 

//funcção para cadastrar funcionario
async function cadastrar_login(){
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    try {
        let response = await fetch("/Usuario", {
            method: "POST",
            headers: {  
                        "usuario:": username,
                        "senha:": password, 
                        "cod_funcionario:":1,
                        "status:": 1,
                        "tipo_usuario:": 1
                     }
        });
    } catch (error) {
        alert("Erro ao cadastrar login: " + error);
    }
}

//funcção para cadastrar fornecedor
async function cadastrar_fornecedor() {
  const nome_fornecedor = document.getElementById("nome_fornecedor").value;
  const cnpj = document.getElementById("cnpj").value;

  try {
    const response = await fetch("/Fornecedores", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ nome_fornecedor, cnpj })
    });

    if (!response.ok) {
      throw new Error("Erro HTTP: " + response.status);
    }

    alert("Fornecedor cadastrado com sucesso!");
  } catch (error) {
    alert("Erro ao cadastrar fornecedor: " + error);
  }
}
