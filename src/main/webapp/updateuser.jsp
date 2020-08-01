<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO" import="it.contrader.model.User.Usertype"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="User Edit page">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Edit User</title>

</head>
<body>
<%@ include file="./css/header.jsp" %>
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

<%UserDTO u = (UserDTO) request.getSession().getAttribute("dto");%>


<form id="center" action="/user/update" method="post">
  <div class="row">
    <div class="col-25">
      <label for="user">Username</label>
    </div>
    <div class="col-75">
      <input type="text" id="user" name="username" value=<%=u.getUsername()%>>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="pass">Password</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="pass" name="password" value=<%=u.getPassword()%>> 
    </div>
  </div>
	<%
		if ((u.getUsertype() != Usertype.SUPERUSER && u.getUsertype() != Usertype.ADMIN) || (u.getUsertype() == Usertype.ADMIN && user.getUsertype() == Usertype.SUPERUSER)) {
	%>
  <div class="row">
    <div class="col-25">
      <label for="type">Usertype</label>
    </div>
   		 <div class="col-75">
 			<select id="type" name="usertype">
  				<option value="ADMIN" <%if(u.getUsertype().toString().equals("ADMIN")) {%>selected<%}%>>ADMIN</option>
  				<option value="USER" <%if(u.getUsertype().toString().equals("USER")) {%>selected<%}%>>USER</option>
				<option value="CORRIERE" <%if(u.getUsertype().toString().equals("CORRIERE")) {%>selected<%}%>>CORRIERE</option>
			</select>
    	</div>
    	<input type="hidden" name="id" value =<%=u.getId() %>>
  </div>
 	<%
		}
	%>
      <button type="submit" >Edit</button>
</form>

	
</div>
<br>
<%@ include file="./css/footer.jsp" %>
<script type="text/javascript" src="/js/subMenu.js"></script>
</body>
</html>