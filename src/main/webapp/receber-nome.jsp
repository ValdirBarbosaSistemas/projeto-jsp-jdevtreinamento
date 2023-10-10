<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Projeto JSP JDEV Treinamentos</title>
</head>
<body>
	<%
		String nome = request.getParameter("nome");//RECEBENDO O NOME DO FORMULARIO COMO PARAMETRO NA REQUISICAO
		String idade = request.getParameter("idade");
		
		out.print(nome);
		out.print(idade);
	%>
</body>
</html>