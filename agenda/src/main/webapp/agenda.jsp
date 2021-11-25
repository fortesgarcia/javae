<%@page import="java.util.concurrent.ForkJoinPool"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@	page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	@ SuppressWarnings ("unchecked")
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/favicon64x64.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de contatos</h1>
	<a href="novo.html" class="botao1">Novo contato</a>
	<a href="report" class="botao2">Relatorio</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th align="center">Ação</th>				
			</tr>
		</thead>
		<tbody>		
			<%
			//for para popular os dados no formulario html
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getIdContato()%></td>
				<td><%=lista.get(i).getNomeContato()%></td>
				<td><%=lista.get(i).getFoneContato()%></td>
				<td><%=lista.get(i).getEmailContato()%></td>
				<td><a href="editar?idcon=<%=lista.get(i).getIdContato()%>" class="botao1">Editar</a>
				<a href="javascript: confirmar(<%=lista.get(i).getIdContato()%>)" class="botao2">Excluir</a></td>
			</tr>			
			<%}%>			
		</tbody>

	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>