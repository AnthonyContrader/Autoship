<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.CodiceDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Spedizione Management">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
</head>
<body>
	<%@ include file="../css/header.jsp" %>
	
	<div class="navbar">
	  <a href="/homecorriere.jsp">Home</a>
	  <a class="active"  href="/spedizione/getall">Ordini</a>
	  <a href="/user/logout" id="logout">Logout</a>
	</div>
	
<div class="main">
	<%
		List<CodiceDTO> list = (List<CodiceDTO>) request.getAttribute("list");
	%>

	<br>

	<table class="tableRead">
		<tr>
			<th>Codice</th>
			<th></th>
		</tr>
		<%
			for (CodiceDTO c : list) {
		%>
		<tr>
			<td><%=c.getOtp()%></td>
			<td><a href="/spedizione/delete?id=<%=c.getId()%>">Spedisci</a>
			</td>
			<%
				}
			%>
		</tr>
	</table>


</div>

<%@ include file="../css/footer.jsp" %>
</body>
<body>

</body>
</html>