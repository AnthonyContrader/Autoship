<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.MagazzinoDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read User</title>
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


<table>
	<tr> 
		
			<th>Oggetto</th>
			<th>Capienza</th>
	</tr>
	<tr>
		<td><%=m.getId_oggetto()%></td>
		<td><%=m.getCapienza()%></td>
	</tr>	
</table>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>


</div>

<%@ include file="../css/footer.jsp" %>
</body>
</html>