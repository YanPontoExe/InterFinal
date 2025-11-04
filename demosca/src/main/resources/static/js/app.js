//funções para navegação entre páginas, pelos button's
function entrar_materiais(){
    window.location.href = "access-material.html";
}

function return_index(){
    window.location.href = "index.html";
}

function entrar_usuarios(){
    window.location.href = "access-user.html";
}

function entrar_fornecedores(){
    window.location.href = "access-fornecedor.html";
}

function entrar_marcas(){
    window.location.href = "access-marca.html";
}

//funcção para cadastrar login do funcionario
async function cadastrar_login(){
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    try {
        let response = await fetch("/Usuario", {
            method: "POST",
            headers: {  
                        "nome:": username,
                        "senha:": password
                     }
        });
    } catch (error) {
        alert("Erro ao cadastrar login: " + error);
    }
}