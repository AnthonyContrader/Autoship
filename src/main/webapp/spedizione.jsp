<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.CodiceDTO" import="it.contrader.dto.UserDTO"
	import="it.contrader.model.User.Usertype"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Spedizione Management">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Spedizione Manager</title>
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
			<a href="/homecorriere.jsp">Home</a>
		<%
		 	}
			if(user.getUsertype() == Usertype.SUPERUSER){
		%>
			<a href=/user/getall>Users</a>
			<a href=/magazzino/getall>Magazzino</a>
			<a href=/oggetto/getall>Oggetto</a>
		<%
			}
		%>
		  <a class="active"  href="/spedizione/getall">Ordine</a>
		<%
			if(user.getUsertype() == Usertype.SUPERUSER){
		%>
			<a href="/acquisto/getall">Acquisto</a>
	  		<a href="/carrello/getall">Carrello</a>
		<%
			}
		%>
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
					<a href="/homecorriere.jsp">Home</a>
				<%
				 	}
					if(user.getUsertype() == Usertype.SUPERUSER){
				%>
					<a href=/user/getall>Users</a>
					<a href=/magazzino/getall>Magazzino</a>
					<a href=/oggetto/getall>Oggetto</a>
				<%
					}
				%>
				  <a class="active"  href="/spedizione/getall">Ordine</a>
				<%
					if(user.getUsertype() == Usertype.SUPERUSER){
				%>
					<a href="/acquisto/getall">Acquisto</a>
			  		<a href="/carrello/getall">Carrello</a>
				<%
					}
				%>
			</div>
		</div>
	</div>
	
<div class="main">
<br>
	<%
		List<CodiceDTO> list = (List<CodiceDTO>) request.getAttribute("list");
	%>

	<table class="tableRead">
		<tr>
			<th>Codice</th>
			<th></th>
		</tr>
		<%
			if(list.isEmpty()) {
		%>
			<tr>
				<td>No data for Ordine</td>
				<td></td>
			</tr>
		<%
			}
			else{
				for (CodiceDTO c : list) {
		%>
		<tr>
			<td><%=c.getOtp()%></td>
			<td><a href="/spedizione/delete?id=<%=c.getId()%>">Spedisci</a>
			</td>
			<%
				}
			}
			%>
		</tr>
	</table>


</div>

<%@ include file="../css/footer.jsp" %>
<script type="text/javascript" src="/js/subMenu.js"></script>
</body>
<body>

</body>
</html>