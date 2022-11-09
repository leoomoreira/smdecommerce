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
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
    if (produtos != null && !produtos.isEmpty()) {
        NumberFormat numberFormat = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
%>
<table class="table table-striped table-hover">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Descrição</th>
            <th scope="col">Quantidade</th>
            <th scope="col">Preço</th>
            <th scope="col"></th>
        </tr>
    </thead>
    <tbody>
        <%
            for (int i = 0; i < produtos.size(); i++) {
                Produto p = produtos.get(i);
        %>
        <tr>
            <th scope="row"><%= i + 1 %></th>
            <td><%= p.getDescricao() %></td>
            <td><%= p.getQuantidade() %></td>
            <td><%= numberFormat.format(p.getPreco()) %></td>
            <td><a class="btn btn-primary" href="MostrarProduto?id=<%= p.getId() %>" role="button">Alterar</a></td>
        </tr>
        <%
            }    
        %>
    </tbody>
</table>
<%
    }
%>
<%@include file="../rodape.jsp" %>
