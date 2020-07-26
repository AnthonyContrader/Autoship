<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.OggettoDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Oggetto Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a  href="homeadmin.jsp">Home</a>
  <a href="UserServlet?mode=userlist">Users</a>
  <a href="MagazzinoServlet?mode=magazzinolist">Magazzino</a>
  <a class="active" href="OggettoServlet?mode=oggettolist">Oggetto</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<OggettoDTO> list = (List<OggettoDTO>) request.getAttribute("list");
	%>

<br>

	<table class="tableInsert">
		<tr>
			<th>Oggetto</th>
			<th>Dimensione</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (OggettoDTO o : list) {
		%>
		<tr>
			<td><%=o.getNome()%></td>
			<td><%=o.getDimensione()%></td>
			<% if(o.getCella() == false && o.getCancellato() == 0) { %>
			<td><a href=OggettoServlet?mode=read&update=true&id=<%=o.getId()%>>Edit</a>
			</td>
			<td><a href=OggettoServlet?mode=delete&id=<%=o.getId()%>>Delete</a>
			</td>
			<% 
				}
				else if(o.getCancellato() == 1){
			%>
			<td><a href=OggettoServlet?mode=reinsert&id=<%=o.getId()%>>Out of Order</a></td>
			<td></td>
			<% 
				}
				else{
			%>
			<td></td>
			<td></td>
			<%					
				}
			%>
		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="OggettoServlet?mode=insert" method="post">
  <div class="row">
    <div class="col-25">
      <label for="nome">Oggetto</label>
    </div>
    <div class="col-75">
      <input type="text" id="nome" name="nome" placeholder="inserisci nome oggetto">
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
      <button type="submit" >Insert</button>
</form>

</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>