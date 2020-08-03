<%@ page import="it.contrader.dto.UserDTO" import="it.contrader.model.User.Usertype" import="java.util.*"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="User Management">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>User Manager</title>

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
			<a class="active"  href=/user/getall>Utente</a>
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
					<a class="active"  href=/user/getall>Utente</a>
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
			List<UserDTO> list = (List<UserDTO>) request.getSession().getAttribute("list");
		%>

		<table class=tableInsert>
			<tr>
				<th>Username</th>
				<th>Password</th>
				<th>Tipo</th>
				<th></th>
				<th></th>
			</tr>
			<%
				for (UserDTO ul : list) {
					if(!(ul.getUsertype() == Usertype.SUPERUSER && user.getUsertype() != Usertype.SUPERUSER)){
			%>
			<tr>
				<td><a href="/user/read?id=<%=ul.getId()%>"> <%=ul.getUsername()%>
				</a></td>
				<td><%=ul.getPassword()%></td>
				<td><%=ul.getUsertype()%></td>
				<td><a href="/user/preupdate?id=<%=ul.getId()%>">Modifica</a></td>


				<%
					if ((ul.getUsertype() != Usertype.SUPERUSER && ul.getUsertype() != Usertype.ADMIN) || (ul.getUsertype() == Usertype.ADMIN && user.getUsertype() == Usertype.SUPERUSER)) {
				%>
					<td><a href="/user/delete?id=<%=ul.getId()%>">Elimina</a></td>
				<%
					}
					else {
				%>
					<td></td>
				<%
					}
				%>

			</tr>
			<%
				}
			}
			%>
		</table>



		<form id="floatright" action="/user/insert" method="post">
			<div class="row">
				<div class="col-25">
					<label for="user">Username</label>
				</div>
				<div class="col-75">
					<input type="text" id="user" name="username"
						placeholder="inserisci username">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="pass">Password</label>
				</div>
				<div class="col-75">
					<input type="text" id="pass" name="password"
						placeholder="inserisci password">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="type">Tipo</label>
				</div>
				<div class="col-75">
					<select id="type" name="usertype">
						<option value="ADMIN">ADMIN</option>
						<option value="USER">USER</option>
						<option value="CORRIERE">CORRIERE</option>

					</select>
				</div>
			</div>
			<button type="submit">Inserisci</button>
		</form>

	</div>
	<br>
	<%@ include file="./css/footer.jsp"%>
	<script type="text/javascript" src="/js/subMenu.js"></script>
</body>
</html>