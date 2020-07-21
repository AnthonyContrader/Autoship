<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
    import="it.contrader.dto.CodiceDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homecorriere.jsp">Home</a>
  <a class="active" href="SpedizioneServlet?mode=codicelist">Ordini</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">
<%
		List<CodiceDTO> list = (List<CodiceDTO>) request.getAttribute("list");
	%>

<br>

	<table>
		<tr>
			<th>Codice</th>
			<th></th>
		</tr>
		<%
			for (CodiceDTO c : list) {
		%>
		<tr>
			<td><%=c.getOtp()%></td>
			<td><a href=SpedizioneServlet?mode=update&id=<%=c.getId()%>>Spedisci</a>
			</td>
			<%
				}
			%>
		</tr>
	</table>


</div>

<%@ include file="../css/footer.jsp" %>
</body>
</html>