<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List" 
    import="it.contrader.dto.MagazzinoDTO" import="it.contrader.dto.OggettoDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit User</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a href="UserServlet?mode=userlist">Users</a>
  <a class="active" href="MagazzinoServlet?mode=magazzinolist">Magazzino</a>
  <a href="OggettoServlet?mode=oggettolist">Oggetto</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%MagazzinoDTO m = (MagazzinoDTO) request.getAttribute("dto");%>
<%List<OggettoDTO> oggetti = (List<OggettoDTO>) request.getAttribute("oggetti");%>

<br>

	<table>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Dimensione</th>
		</tr>
		<%
		if(oggetti!=null){
			for (OggettoDTO o : oggetti) {
		%>
		<tr>
			<td><%=o.getId()%></td>
			<td><%=o.getNome()%></td>
			<td><%=o.getDimensione()%></td>
		</tr>
		<%
			}}
		%>
	</table>


<form id="floatright" action="MagazzinoServlet?mode=update&id=<%=m.getId()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="idoggetto">Oggetto</label>
    </div>
    <select id="id_oggetto" name="id_oggetto">
	    	<% 
	    		for(OggettoDTO o : oggetti) {
	    	%>
	  			<option value=<%=o.getId()%>><%=o.getNome()%></option>
		 	<%
		 		} 
		 	%> 
		</select>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="capienza">Capienza</label>
    </div>
    <div class="col-75">
      <input type="number" id="capienza" name="capienza" placeholder="inserisci capienza">
    </div>
  </div>
      <button type="submit" >Edit</button>
</form>

	
</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>