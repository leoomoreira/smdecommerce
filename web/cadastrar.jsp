<%-- 
    Document   : cadastrar
    Author     : Leonardo Oliveira Moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="cabecalho.jsp" %>
<div class="alert alert-primary mt-3" role="alert">Novo Cliente</div>
<form action="InserirNovoCliente" method="post">
    <div class="form-group mt-2 mb-2">
        <label for="inputNome" class="mb-2">Nome</label>
        <input type="text" name="nome" class="form-control" id="inputNome" aria-describedby="nomeHelp" placeholder="Entre com seu nome" />
    </div>
    <div class="form-group mt-2 mb-2">
        <label for="inputEndereco" class="mb-2">Endereço</label>
        <input type="text" name="endereco" class="form-control" id="inputEndereco" aria-describedby="enderecoHelp" placeholder="Entre com seu endereço" />
    </div>
    <div class="form-group mt-2 mb-2">
        <label for="inputEmail" class="mb-2">E-mail</label>
        <input type="email" name="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" placeholder="Entre com seu e-mail" />
    </div>
    <div class="form-group mt-2 mb-2">
        <label for="inputLogin" class="mb-2">Login</label>
        <input type="text" name="login" class="form-control" id="inputLogin" aria-describedby="loginHelp" placeholder="Entre com seu login" />
    </div>
    <div class="form-group mt-2 mb-2">
        <label for="inputEmail" class="mb-2">Senha</label>
        <input type="password" name="senha" class="form-control" id="inputSenha" aria-describedby="senhaHelp" placeholder="Entre com sua senha" />
    </div>
    <button type="submit" class="btn btn-primary mt-2">Inserir</button>
</form>
<%@include file="rodape.jsp" %>