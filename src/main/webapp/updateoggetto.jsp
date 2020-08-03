<%@page import="it.contrader.dto.OggettoDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.OggettoDTO"
    import="it.contrader.dto.UserDTO" import="it.contrader.model.User.Usertype"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Oggetto Edit page">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Edit Oggetto</title>
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
			<a href="/homeadmin.jsp">Home</a>
		<%
		 	}
		%>
			<a href=/user/getall>Utente</a>
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
					<a href=/user/getall>Utente</a>
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
			</div>
		</div>
	</div>
<br>
<div class="main">
<br>

<%OggettoDTO o = (OggettoDTO) request.getAttribute("dto");%>


<form id="center" action="/oggetto/update" method="post">
   <div class="row">
    <div class="col-25">
      <label for="nome">Nome</label>
    </div>
    <div class="col-75">
      <input type="text" id="nome" name="nome" value="<%=o.getNome()%>" required>
    </div>
  </div>
       <div class="row">
    <div class="col-25">
      <label for="dimensione">Dimensione</label>
    </div>
    <div class="col-75">
      <input type="number" id="dimensione" name="dimensione" value="<%=o.getDimensione()%>" required>
    </div>
    <input type="hidden" name="id" value =<%=o.getId() %>>
  </div>
      <button type="submit" >Modifica</button>
</form>

	
</div>
<br>
<%@ include file="../css/footer.jsp" %>
<script type="text/javascript" src="/js/subMenu.js"></script>	
</body>
</html>