< %@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.OggettoDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a href="UserServlet?mode=userlist">Users</a>
  <a class="active" href="OggettoServlet?mode=oggettolist">Oggetto</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">
<% OggettoDTO m = (OggettoDTO) request.getAttribute("dto");%>


<table>
	<tr> 
		
			<th>Oggetto</th>
			<th>Dimensione</th>
	</tr>
	<tr>
		<td><%=m.getId_oggetto()%></td>
		<td><%=m.getDimensione()%></td>
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