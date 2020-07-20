<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.MagazzinoDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Magazzino Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a  href="homeadmin.jsp">Home</a>
  <a href="UserServlet?mode=userlist">Users</a>
  <a class="active" href="MagazzinoServlet?mode=magazzinolist">Magazzino</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<MagazzinoDTO> list = (List<MagazzinoDTO>) request.getAttribute("list");
	%>

<br>

	<table>
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
			<td><%=m.getId_oggetto()%></td>
			<td><%=m.getCapienza()%></td>
			<td><a href=MagazzinoServlet?mode=read&update=true&id=<%=m.getId()%>>Edit</a>
			</td>
			<td><a href=MagazzinoServlet?mode=delete&id=<%=m.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="MagazzinoServlet?mode=insert" method="post">
  <div class="row">
    <div class="col-25">
      <label for="id_oggetto">Oggetto</label>
    </div>
    <div class="col-75">
      <input type="number" id="id_oggetto" name="id_oggetto" placeholder="inserisci id oggetto">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="capienza">Capienza</label>
    </div>
    <div class="col-75">
      <input type="number" id="capienza" name="capienza" placeholder="inserisci capienza">
    </div>
  </div>
      <button type="submit" >Insert</button>
</form>

</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>