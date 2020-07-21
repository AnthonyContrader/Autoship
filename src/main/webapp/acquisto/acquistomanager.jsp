<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.OggettoDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Acquisto Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a  href="homeuser.jsp">Home</a>
  <a class="active" href="AcquistoServlet?mode=oggettolist">Oggetto</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<OggettoDTO> list = (List<OggettoDTO>) request.getAttribute("list");
	%>

<br>

	<table>
		<tr>
			<th>Oggetto</th>
			<th>Dimensione</th>
			<th></th>
		</tr>
		<%
			for (OggettoDTO o : list) {
		%>
		<tr>
			<td><%=o.getNome()%></td>
			<td><%=o.getDimensione()%></td>
			<td><a href=AcquistaServlet?mode=read&update=true&id=<%=o.getId()%>>Ordina</a>
			</td>
			<%
				}
			%>
		</tr>
	</table>


</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>