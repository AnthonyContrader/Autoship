<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.contrader.dto.OggettoDTO"
	import="it.contrader.dto.UserDTO" import="it.contrader.model.User.Usertype"%>
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
	<%
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
	%>
	<div class="navbar">
		<%
		if(user.getUsertype() == Usertype.SUPERUSER){
	%>
	  <a href="/homesuperuser.jsp">Home</a>
	<%
		}
	 	else{
	%>
		<a href="/homeadmin.jsp">Home</a>
	<%
	 	}
	%>
		<a href=/user/getall>Users</a>
		<a href=/magazzino/getall>Magazzino</a>
		<a class="active" href=/oggetto/getall>Oggetto</a>
	<%
		if(user.getUsertype() == Usertype.SUPERUSER){
	%>
		<a href="/spedizione/getall">Ordine</a>
		<a href="/acquisto/getall">Acquisto</a>
  		<a href="/carrello/getall">Carrello</a>
	<%
		}
	%>
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