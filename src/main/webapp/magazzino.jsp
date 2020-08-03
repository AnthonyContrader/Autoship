<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.MagazzinoDTO"
	import="it.contrader.dto.OggettoDTO" import="it.contrader.dto.UserDTO"
	import="it.contrader.model.User.Usertype"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Magazzino Management">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Gestione Magazzino</title>
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
			<a class="active" href=/magazzino/getall>Magazzino</a>
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
					<a href=/user/getall>Utente</a>
					<a class="active" href=/magazzino/getall>Magazzino</a>
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
		List<MagazzinoDTO> list = (List<MagazzinoDTO>) request.getAttribute("list");
		List<OggettoDTO> ol = (List<OggettoDTO>) request.getAttribute("oggetti");
	%>

	<table class="tableInsert">
		<tr>
			<th>Oggetto</th>
			<th>Capienza</th>
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
				for (MagazzinoDTO m : list) {
			%>
			<tr>
			<%
				if(m.getOggetto() != null){
			%>
				<td><%=m.getOggetto().getNome()%></td>
			<%
				}
				else{
			%>
				<td>Vuoto</td>
			<%
				}
			%>
			<td><%=m.getCapienza()%></td>
			<%
				if(m.isCancellato() == false){
					if(m.getCodice()==null){
			%>
			<td><a href=/magazzino/preupdate?id=<%=m.getId()%>>Modifica</a>
			</td>
			<%
					}
					else{
			%>
			<td></td>
			<%
					}
					if(m.getOggetto() != null) {
			%>
				<td></td>
			<%
					}
					else{
			%>
				<td><a href=/magazzino/delete?id=<%=m.getId()%>>Elimina</a></td>
			<%
					}
				}
				else{
			%>
				<td><a href=/magazzino/reinsert?id=<%=m.getId()%>>Disattivato</a></td>
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



<form id="floatright" action="/magazzino/insert" method="post">
  <div class="row">
    <div class="col-25">
      <label for="id_oggetto">Oggetto</label>
    </div>
    <div class="col-75">
	   <select id="id_oggetto" name="id_oggetto">
	    	<% 
	    		for(OggettoDTO o : ol) {
	    	%>
	  			<option value=<%=o.getId()%>><%=o.getNome()%></option>
		 	<%
		 		} 
		 	%> 
		 	<option value="0">Vuoto</option>
		</select>
     </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="capienza">Capienza</label>
    </div>
    <div class="col-75">
      <input type="number" id="capienza" name="capienza" placeholder="inserisci capienza" required>
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