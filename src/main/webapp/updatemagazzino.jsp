<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List" 
    import="it.contrader.dto.MagazzinoDTO" import="it.contrader.dto.OggettoDTO"
    import="it.contrader.dto.UserDTO" import="it.contrader.model.User.Usertype"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Magazzino Edit page">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Edit Magazzino</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
	<%
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
	%>
	<div class="navbar">
		<a href="/homeadmin.jsp">Home</a>
		<a href=/user/getall>Users</a>
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
<br>
<div class="main">

<%MagazzinoDTO m = (MagazzinoDTO) request.getAttribute("dto");%>
<%List<OggettoDTO> oggetti = (List<OggettoDTO>) request.getAttribute("listo");%>

<br>

<form id="center" action="/magazzino/update?id=<%=m.getId()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="id_oggetto">Oggetto</label>
    </div>
    <div class="col-75">
	    <select id="id_oggetto" name="id_oggetto">
	    	<%
				if (m.getOggetto() != null) {
			%>
				<option value="<%=m.getOggetto().getId()%>"><%=m.getOggetto().getNome()%></option>
			<%
				}
			%>
		    <% 
		   		for(OggettoDTO o : oggetti) {
		   	%>
		 		<option value="<%=o.getId()%>"><%=o.getNome()%></option>
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
      <input type="number" id="capienza" name="capienza" value="<%=m.getCapienza()%>" required>
    </div>
  </div>
      <button type="submit" >Edit</button>
</form>

	
</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>