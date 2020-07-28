<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.contrader.dto.OggettoDTO"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Oggetto Read">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Read Oggetto</title>
</head>
<body>
	<%@ include file="./css/header.jsp"%>
	<div class="navbar">
		<a href="/homeadmin.jsp">Home</a>
		<a href=/user/getall>Users</a>
		<a href=/magazzino/getall>Magazzino</a>
		<a class="active" href=/oggetto/getall>Oggetto</a>
		<a href="/user/logout" id="logout">Logout</a>
	</div>
</body>
<br>
	<div class="main">
		<%
			OggettoDTO o = (OggettoDTO) request.getAttribute("dto");
		%>


		<table id="center">
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Dimensione</th>
			</tr>
			<tr>
				<td><%=o.getId()%></td>
				<td><%=o.getNome()%></td>
				<td><%=o.getDimensione()%></td>
			</tr>
		</table>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>


	</div>

	<%@ include file="./css/footer.jsp"%>
	
</html>