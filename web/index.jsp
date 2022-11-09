<%-- 
    Document   : index
    Author     : Leonardo Oliveira Moreira
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="carrinhocompra.modelo.CarrinhoCompraItem"%>
<%@page import="produto.modelo.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="cabecalho.jsp" %>
<%
    NumberFormat numberFormat = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
    List<Produto> produtosEmEstoque = (List<Produto>) request.getAttribute("produtosEmEstoque");
    if (produtosEmEstoque != null) {
%>
<div class="alert alert-primary mt-3" role="alert">
    Produtos em Estoque
</div>
<div class="row row-cols-1 row-cols-md-4 g-4">
<%
        for (Produto pe : produtosEmEstoque) {
%>
    <div class="col">
        <div class="card h-100">
            <img src="<%= pe.getFoto() == null ? "..." : "ExibirFotoProduto?id=" + pe.getId() %>" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title"><%= pe.getDescricao() %></h5>
                <p class="card-text">
                    Preço <strong>R$ <%= numberFormat.format(pe.getPreco()) %></strong><br/>
                    <a href="AdicionarCarrinhoProduto?produtoId=<%= pe.getId() %>" class="btn btn-danger mt-2">Adicionar</a>
                </p>
            </div>
            <div class="card-footer"><small class="text-muted">Quantidade: <strong><%= pe.getQuantidade() %></strong></small></div>
        </div>
    </div>
<%
        }
%>
</div>
<%
    } else {
%>
<h4>Nenhum produto encontrado</h4>
<%
    }
%>
<%
    List<CarrinhoCompraItem> produtosCarrinhoCompra = (List<CarrinhoCompraItem>) request.getAttribute("produtosCarrinhoCompra");
    if (produtosCarrinhoCompra != null) {
%>
<div class="alert alert-primary mt-3" role="alert">
    Carrinho de Compra
</div>
<div class="row row-cols-1 row-cols-md-4 g-4">
<%
        double total = 0;
        for (CarrinhoCompraItem cci : produtosCarrinhoCompra) {
            total += cci.getProduto().getPreco() * cci.getQuantidade();
%> 
    <div class="col">
        <div class="card h-100">
            <img src="<%= cci.getProduto().getFoto() == null ? "..." : "ExibirFotoProduto?id=" + cci.getProduto().getId() %>" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title"><%= cci.getProduto().getDescricao() %></h5>
                <p class="card-text">
                    Preço <strong>R$ <%= numberFormat.format(cci.getProduto().getPreco()) %></strong><br/>
                    <a href="RemoverCarrinhoProduto?produtoId=<%= cci.getProduto().getId() %>" class="btn btn-success mt-2">Remover</a>
                </p>
            </div>
            <div class="card-footer"><small class="text-muted">Quantidade: <strong><%= cci.getQuantidade() %></strong></small></div>
        </div>
    </div>
<%
        }
%>
</div>
<div class="alert alert-warning mt-3" role="alert">
    Total <strong>R$ <%= numberFormat.format(total) %></strong>
</div>
<%
    }
%>
<%@include file="rodape.jsp" %>