<%-- 
    Document   : listarProduto
    Author     : Leonardo Oliveira Moreira
--%>

<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="produto.modelo.Produto" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../cabecalho.jsp" %>
<div class="alert alert-primary mt-3" role="alert">Cadastros de Produtos</div>
<%
    Produto produto = (Produto) request.getAttribute("produto");
    if (produto != null) {
        NumberFormat numberFormat = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
%>
<form action="AlterarProduto" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="<%= produto.getId() %>" />
    <div class="form-group mt-2 mb-2">
        <label for="inputDescricao" class="mb-2">Descrição</label>
        <input type="text" name="descricao" class="form-control" id="inputDescricao" aria-describedby="descricaoHelp" value="<%= produto.getDescricao() %>" />
    </div>
    <div class="form-group mt-2 mb-2">
        <label for="inputQuantidade" class="mb-2">Quantidade</label>
        <input type="text" name="quantidade" class="form-control" id="inputQuantidade" aria-describedby="quantidadeHelp" value="<%= produto.getQuantidade() %>" />
    </div>
    <div class="form-group mt-2 mb-2">
        <label for="inputPreco" class="mb-2">Preço</label>
        <input type="text" name="preco" class="form-control" id="inputPreco" aria-describedby="precolHelp" value="<%= produto.getPreco() %>" />
    </div>
    <% 
        if (produto.getFoto() != null) {
    %>
    <input type="hidden" name="fotoAtual" value="<%= produto.getFoto() %>" />
    <div class="form-group mt-2 mb-2">
        <label for="inputFotoAtual" class="mb-2">Foto Atual</label><br />
        <img src="ExibirFotoProduto?id=<%= produto.getId() %>" width="300" />
    </div>
    <% } %>
    <div class="form-group mt-2 mb-2">
        <label for="inputFotoNovo" class="mb-2">Nova Foto</label>
        <input type="file" name="foto" class="form-control" id="inputFotoNovo" aria-describedby="fotoNovaHelp" />
    </div>
    <button type="submit" class="btn btn-primary mt-2 mb-3">Alterar</button>
</form>
<%
    }
%>
<%@include file="../rodape.jsp" %>
