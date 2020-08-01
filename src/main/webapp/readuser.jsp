<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"
	import="it.contrader.model.User.Usertype"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="User Read">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Read User</title>
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
			<a class="active"  href=/user/getall>Users</a>
			<a href=/magazzino/getall>Magazzino</a>
			<a href=/oggetto/getall>Oggetto</a>
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
					<a href="/homeadmin.jsp">Home</a>
				<%
				 	}
				%>
					<a class="active"  href=/user/getall>Users</a>
					<a href=/magazzino/getall>Magazzino</a>
					<a href=/oggetto/getall>Oggetto</a>
				<%
					if(user.getUsertype() == Usertype.SUPERUSER){
				%>
					<a href="/spedizione/getall">Ordine</a>
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
			UserDTO u = (UserDTO) request.getSession().getAttribute("dto");
		%>


		<table id="center">
			<tr>
				<th>ID</th>
				<th>Username</th>
				<th>Password</th>
				<th>Usertype</th>
			</tr>
			<tr>
				<td><%=u.getId()%></td>
				<td><%=u.getUsername()%></td>
				<td><%=u.getPassword()%></td>
				<td><%=u.getUsertype()%></td>
			</tr>
		</table>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>


	</div>

	<%@ include file="./css/footer.jsp"%>
	<script type="text/javascript" src="/js/subMenu.js"></script>
</body>
</html>