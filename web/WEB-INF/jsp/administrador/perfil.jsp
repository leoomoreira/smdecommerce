<%-- 
    Document   : perfil
    Author     : Leonardo Oliveira Moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../cabecalho.jsp" %>
<jsp:useBean id="administrador" class="usuario.modelo.Usuario" scope="session" />
<jsp:getProperty name="administrador" property="nome" /><br/>
<jsp:getProperty name="administrador" property="endereco" /><br/>
<jsp:getProperty name="administrador" property="email" /><br/>
<jsp:getProperty name="administrador" property="login" /><br/>
<%@include file="../rodape.jsp" %>
