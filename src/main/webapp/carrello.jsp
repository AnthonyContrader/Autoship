<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.CarrelloDTO" import="it.contrader.model.Carrello.CarrelloStato"
	import="it.contrader.dto.UserDTO" import="it.contrader.model.User.Usertype"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Carrello Management">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Gestione Carrello</title>
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
			<a href=/user/getall>Utente</a>
			<a href=/magazzino/getall>Magazzino</a>
			<a href=/oggetto/getall>Oggetto</a>
			<a href="/spedizione/getall">Ordine</a>
		<%
			}
		%>
		  <a href="/acquisto/getall">Acquisto</a>
		  <a class="active" href="/carrello/getall">Carrello</a>
		  <a href="/user/logout" id="logout">Logout</a>
	</div>
	
	<div class="navbar-small">
		<div>
			<button type="button" onclick="showSubMenu()">Menu</button>
			<a href="/user/logout" id="logout">Logout</a>
		</div>
		<div id="sub-menu-container">
			<div id="sub-menu">
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
		<a href=/user/getall>Utente</a>
		<a href=/magazzino/getall>Magazzino</a>
		<a href=/oggetto/getall>Oggetto</a>
		<a href="/spedizione/getall">Ordine</a>
	<%
		}
	%>
	  <a href="/acquisto/getall">Acquisto</a>
	  <a class="active" href="/carrello/getall">Carrello</a>
			</div>
		</div>
	</div>
	
<div class="main">
<br>
	<%
		List<CarrelloDTO> list = (List<CarrelloDTO>) request.getAttribute("list");
	%>

	<table class="tableRead">
		<tr>
			<th>Username</th>
			<th>Oggetto</th>
			<th>Stato</th>
			<th></th>
		</tr>
		<%
			if(list.isEmpty()) {
		%>
			<tr>
				<td>Nessun dato</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		<%
			}
			else{
				for (CarrelloDTO c : list) {
		%>
		<tr>
			<td><%=c.getUser().getUsername()%></td>
			<td><%=c.getOggetto().getNome()%></td>
			<td><%=c.getStato()%></td>
			<%
				if(c.getStato() == CarrelloStato.Ordinato) {
			%>
			<td><a href="/carrello/delete?id=<%=c.getId()%>">Elimina</a></td>
			<%
				}
				else {
			%>
				<td></td>
			<%
					}
				}
			}
			%>
		</tr>
	</table>

</div>
<br>
<%@ include file="../css/footer.jsp" %>
<script type="text/javascript" src="/js/subMenu.js"></script>
</body>
</html>