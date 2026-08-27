// AREA DE CADASTRO E LOGIN

const titulo = document.getElementById("tituloPrin");
const descricao = document.getElementById("textDescribe");
const form = document.getElementById("formArea");
const botao = document.getElementById("btnLogin");
const textoTroca = document.getElementById("criarConta");

let modoCadastro = false;

// ===== BOTÃO LOGIN / CADASTRO =====
botao.addEventListener("click", function () {

    if (modoCadastro) {

        // ===== CADASTRO =====
        const nome = document.getElementById("nome").value;
        const email = document.getElementById("emailCadastro").value;
        const senha = document.getElementById("senhaCadastro").value;
        const confirmarSenha = document.getElementById("confirmarSenha").value;

        if (senha !== confirmarSenha) {
            alert("As senhas não coincidem!");
            return;
        }

        fetch("http://localhost:8080/usuarios", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                nome: nome,
                email: email,
                senha: senha
            })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao cadastrar");
            }
            return response.json();
        })
        .then(data => {
            alert("Usuário cadastrado com sucesso! Faça login.");
            modoCadastro = false;
            textoTroca.click(); // volta automaticamente para login
        })
        .catch(error => {
            alert("Erro ao cadastrar usuário.");
            console.error(error);
        });

    } else {

        // ===== LOGIN =====
        const email = document.getElementById("email").value;
        const senha = document.getElementById("senha").value;

        fetch("http://localhost:8080/usuarios/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: email,
                senha: senha
            })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Login inválido");
            }
            return response.json();
        })
        .then(data => {

            // 🔐 Salva usuário logado
            localStorage.setItem("usuarioLogado", JSON.stringify(data));

            // 🚀 Redireciona para página do guarda roupas
            window.location.href = "guarda-roupa.html";
        })
        .catch(error => {
            alert("Email ou senha incorretos.");
            console.error(error);
        });
    }
});

// ===== TROCA ENTRE LOGIN E CADASTRO =====
textoTroca.addEventListener("click", () => {

    if (!modoCadastro) {

        // MODO CADASTRO
        titulo.textContent = "CRIAR CONTA";
        descricao.textContent = "crie sua conta no guarda roupas online";

        form.innerHTML = `
            <h3>seu nome</h3>
            <input type="text" id="nome">

            <h3>seu email</h3>
            <input type="email" id="emailCadastro">

            <h3>sua senha</h3>
            <input type="password" id="senhaCadastro">

            <h3>confirmar senha</h3>
            <input type="password" id="confirmarSenha">
        `;

        botao.textContent = "cadastrar";
        textoTroca.innerHTML = 'ja tem conta <a>voltar para login</a>';

        modoCadastro = true;

    } else {

        // MODO LOGIN
        titulo.textContent = "LOGAR";
        descricao.textContent = "comece a usar seu guarda roupas online";

        form.innerHTML = `
            <h3>seu email</h3>
            <input type="email" id="email">

            <h3>sua senha</h3>
            <input type="password" id="senha">
        `;

        botao.textContent = "login";
        textoTroca.innerHTML = 'nao tem conta <a>crie uma agora</a>';

        modoCadastro = false;
    }
});