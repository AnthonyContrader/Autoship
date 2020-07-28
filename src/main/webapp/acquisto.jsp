<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.OggettoDTO"%>
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
	
	<div class="navbar">
	  <a href="/homecorriere.jsp">Home</a>
	  <a class="active" href="/acquisto/getall">Oggetto</a>
	  <a href="/carrello/getall">Ordine</a>
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
			for (OggettoDTO o : list) {
		%>
		<tr>
			<td><%=o.getNome()%></td>
			<td><%=o.getDimensione()%></td>
			<td><a href=/acquisto/update?id=<%=o.getId()%>>Ordina</a>
			</td>
			<%
				}
			%>
		</tr>
	</table>


</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>