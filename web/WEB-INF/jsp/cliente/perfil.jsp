<%-- 
    Document   : perfil
    Author     : Leonardo Oliveira Moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../cabecalho.jsp" %>
<jsp:useBean id="cliente" class="usuario.modelo.Usuario" scope="session" />
<jsp:getProperty name="cliente" property="nome" /><br/>
<jsp:getProperty name="cliente" property="endereco" /><br/>
<jsp:getProperty name="cliente" property="email" /><br/>
<jsp:getProperty name="cliente" property="login" /><br/>
<%@include file="../rodape.jsp" %>
