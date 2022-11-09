<%-- 
    Document   : perfil
    Author     : Leonardo Oliveira Moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="cabecalho.jsp" %>
<div class="alert alert-primary mt-3" role="alert">Perfil do Usuário</div>
<form action="AlterarPerfil" method="post">
    <input type="hidden" name="id" value="<%= usuario.getId() %>" />
    <div class="form-group mt-2 mb-2">
        <label for="inputNome" class="mb-2">Nome</label>
        <input type="text" name="nome" class="form-control" id="inputNome" aria-describedby="nomeHelp" value="<%= usuario.getNome() %>" />
    </div>
    <div class="form-group mt-2 mb-2">
        <label for="inputEndereco" class="mb-2">Endereço</label>
        <input type="text" name="endereco" class="form-control" id="inputEndereco" aria-describedby="enderecoHelp" value="<%= usuario.getEndereco()%>" />
    </div>
    <div class="form-group mt-2 mb-2">
        <label for="inputEmail" class="mb-2">E-mail</label>
        <input type="email" name="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" value="<%= usuario.getEmail() %>" />
    </div>
    <div class="form-group mt-2 mb-2">
        <label for="inputLogin" class="mb-2">Login</label>
        <input type="text" name="login" class="form-control" id="inputLogin" aria-describedby="loginHelp" value="<%= usuario.getLogin() %>" />
    </div>
    <div class="form-group mt-2 mb-2">
        <label for="inputEmail" class="mb-2">Senha</label>
        <input type="password" name="senha" class="form-control" id="inputSenha" aria-describedby="senhaHelp" value="<%= usuario.getSenha() %>"  />
    </div>
    <button type="submit" class="btn btn-primary mt-2 mb-3">Alterar</button>
</form>
<%@include file="rodape.jsp" %>
