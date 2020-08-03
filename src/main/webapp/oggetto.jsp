<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.OggettoDTO" import="it.contrader.dto.UserDTO"
	import="it.contrader.model.User.Usertype"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Oggetto Management">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Gestione Oggetto</title>
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
<div class="main">
<br>
	<%
		List<OggettoDTO> list = (List<OggettoDTO>) request.getAttribute("list");
	%>

	<table class="tableInsert">
		<tr>
			<th>Oggetto</th>
			<th>Dimensione</th>
			<th></th>
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
				for (OggettoDTO o : list) {
		%>
		<tr>
			<td><a href="/oggetto/read?id=<%=o.getId()%>"><%=o.getNome()%></a></td>
			<td><%=o.getDimensione()%></td>
			<% if(o.isCella() == false && o.isCancellato() == false) { %>
				<td><a href="/oggetto/preupdate?id=<%=o.getId()%>">Modifica</a>
				</td>
				<td><a href="/oggetto/delete?id=<%=o.getId()%>">Elimina</a>
				</td>
			<% 
				}
				else if(o.isCancellato() == true){
			%>
				<td><a href="/oggetto/reinsert?id=<%=o.getId()%>">Esaurito</a></td>
				<td></td>
			<% 
				}
				else{
			%>
			<td></td>
			<td></td>
			<%					
				}
			}
			%>
		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="/oggetto/insert" method="post">
  <div class="row">
    <div class="col-25">
      <label for="nome">Oggetto</label>
    </div>
    <div class="col-75">
      <input type="text" id="nome" name="nome" placeholder="inserisci nome oggetto" required>
    </div>
  </div>
  <div class="row">
     <div class="col-25">
      <label for="dimensione">Dimensione</label>
    </div>
    <div class="col-75">
      <input type="number" id="dimensione" name="dimensione" placeholder="inserisci dimensione" required>
    </div>
  </div>
      <button type="submit" >Inserisci</button>
</form>

</div>
<br>
<%@ include file="../css/footer.jsp" %>
<script type="text/javascript" src="/js/subMenu.js"></script>
</body>
</html>