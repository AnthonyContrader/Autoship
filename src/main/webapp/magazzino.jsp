<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.MagazzinoDTO"
	import="it.contrader.dto.OggettoDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Magazzino Management">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
</head>
<body>
<%@ include file="../css/header.jsp" %>

	<div class="navbar">
		<a href="/homeadmin.jsp">Home</a>
		<a href=/user/getall>Users</a>
		<a class="active" href=/magazzino/getall>Magazzino</a>
		<a href=/oggetto/getall>Oggetto</a>
		<a href="/user/logout" id="logout">Logout</a>
	</div>
<div class="main">
	<%
		List<MagazzinoDTO> list = (List<MagazzinoDTO>) request.getAttribute("list");
		List<OggettoDTO> listo = (List<OggettoDTO>) request.getAttribute("oggetti");
	%>

<br>

	<table class="tableInsert">
		<tr>
			<th>Oggetto</th>
			<th>Capienza</th>
			<th></th>
			<th></th>
		</tr>
			<%
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
					if(m.getOtp()==null){
			%>
			<td><a href=/magazzino/preupdate?id=<%=m.getId()%>>Edit</a>
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
				<td><a href=/magazzino/delete?id=<%=m.getId()%>>Delete</a></td>
			<%
					}
				}
				else{
			%>
				<td><a href=/magazzino/reinsert?id=<%=m.getId()%>>Disabled</a></td>
				<td></td>
			<%
				}
			%>
		</tr>
		<%
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
	    		for(OggettoDTO o : listo) {
	    	%>
	  			<option value=<%=o.getId()%>><%=o.getNome()%></option>
		 	<%
		 		} 
		 	%> 
		 	<option value="">Vuoto</option>
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
      <button type="submit" >Insert</button>
</form>

</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>