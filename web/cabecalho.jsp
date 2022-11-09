<%-- 
    Document   : cabecalho
    Author     : Leonardo Oliveira Moreira
--%>

<%@page import="usuario.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    boolean administrador = false;
    Usuario usuario = null;
    if (session.getAttribute("administrador") != null) {
        usuario = (Usuario) session.getAttribute("administrador");
        administrador = true;
    } else {
        if (session.getAttribute("cliente") != null) {
            usuario = (Usuario) session.getAttribute("cliente");
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <title>SMD E-commerce</title>
    </head>
    <body>
        <div class="container">
            <header>
                <nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light border-bottom">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="Inicio">
                            <!--img src="images/icon.png" alt="" width="26" height="26" class="d-inline-block align-text-top"-->
                            SMD E-commerce
                        </a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarToggler01" aria-controls="navbarToggler01" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarToggler01"> 
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                <li class="nav-item">
                                    <a class="nav-link" href="cadastrar.jsp">Cadastre-se</a>
                                </li>
                            </ul>
                            <%
                                if (usuario == null) {
                            %>
                            <form class="d-flex" action="Login" method="post">
                                <input class="form-control me-2" type="text" name="login" placeholder="Login" aria-label="Login" required>
                                <input class="form-control me-2" type="password" name="senha" placeholder="Senha" aria-label="Password" required>
                                <button class="btn btn-outline-primary" type="submit">Login</button>
                            </form>
                            <%
                                } else {
                            %>
                            <form class="d-flex" action="Logout" method="get">
                                <div class="form-control bg-transparent border-0 me-2">Olá, <b><%= usuario.getNome() %></b>! (<%= (administrador) ? "Administrador" : "Cliente" %>)</div>
                                <button class="btn btn-outline-secondary" type="submit">Sair</button>
                            </form>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </nav>
            </header>
            <main>
                <% if (request.getAttribute("mensagem") != null) { %>
                <div class="mb-3"></div>
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong><%= request.getAttribute("mensagem") %></strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <% }%>
                <div class="mb-2"></div>