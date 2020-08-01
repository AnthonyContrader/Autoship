<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.OggettoDTO" import="it.contrader.dto.UserDTO"
	import="it.contrader.model.User.Usertype"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Acquisto Management">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Acquisto Manager</title>
</head>
<body>
	<%@ include file="../css/header.jsp" %>
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
	  <a href="/homeuser.jsp">Home</a>
	<%
	 	}
		if(user.getUsertype() == Usertype.SUPERUSER){
	%>
		<a href=/user/getall>Users</a>
		<a href=/magazzino/getall>Magazzino</a>
		<a href=/oggetto/getall>Oggetto</a>
		<a href="/spedizione/getall">Ordine</a>
	<%
		}
	%>
	  <a class="active" href="/acquisto/getall">Acquisto</a>
	  <a href="/carrello/getall">Carrello</a>
	  <a href="/user/logout" id="logout">Logout</a>
	</div>
	
<div class="main">

<%
		List<OggettoDTO> list = (List<OggettoDTO>) request.getAttribute("list");
	%>

<br>

	<table class="tableRead">
		<tr>
			<th>Oggetto</th>
			<th>Dimensione</th>
			<th></th>
		</tr>
		<%
			if(list.isEmpty()) {
		%>
			<tr>
				<td>No data for Acquisto</td>
				<td></td>
				<td></td>
			</tr>
		<%
			}
			else{
				for (OggettoDTO o : list) {
		%>
		<tr>
			<td><%=o.getNome()%></td>
			<td><%=o.getDimensione()%></td>
			<td><a href=/acquisto/update?id=<%=o.getId()%>>Ordina</a>
			</td>
			<%
				}
			}
			%>
		</tr>
	</table>


</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>