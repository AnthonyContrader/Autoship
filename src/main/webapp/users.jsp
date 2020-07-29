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
		<a href="/homeadmin.jsp">Home</a>
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
	<div class="main">
		<%
			List<UserDTO> list = (List<UserDTO>) request.getSession().getAttribute("list");
		%>

		<br>

		<table class=tableInsert>
			<tr>
				<th>Username</th>
				<th>Password</th>
				<th>Usertype</th>
				<th></th>
				<th></th>
			</tr>
			<%
				for (UserDTO u : list) {
					if(u.getUsertype() == Usertype.SUPERUSER && user.getUsertype() != Usertype.SUPERUSER){
						
					}
					else{
			%>
			<tr>
				<td><a href="/user/read?id=<%=u.getId()%>"> <%=u.getUsername()%>
				</a></td>
				<td><%=u.getPassword()%></td>
				<td><%=u.getUsertype()%></td>
				<td><a href="/user/preupdate?id=<%=u.getId()%>">Edit</a></td>


				<%
					if ((u.getUsertype() != Usertype.SUPERUSER && u.getUsertype() != Usertype.ADMIN) || (u.getUsertype() == Usertype.ADMIN && user.getUsertype() == Usertype.SUPERUSER)) {
				%>
					<td><a href="/user/delete?id=<%=u.getId()%>">Delete</a></td>
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
					<label for="type">Usertype</label>
				</div>
				<div class="col-75">
					<select id="type" name="usertype">
						<option value="ADMIN">ADMIN</option>
						<option value="USER">USER</option>
						<option value="CORRIERE">CORRIERE</option>

					</select>
				</div>
			</div>
			<button type="submit">Insert</button>
		</form>

	</div>
	<br>
	<%@ include file="./css/footer.jsp"%>
</body>
</html>