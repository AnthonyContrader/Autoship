<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>User Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a  href="homeadmin.jsp">Home</a>
  <a class="active" href="UserServlet?mode=userlist">Users</a>
  <a class="active" href="MagazzinoServlet?mode=magazzinolist">Users</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<MagazzinoDTO> list = (List<MagazzinoDTO>) request.getAttribute("list");
	%>

<br>

	<table>
		<tr>
			<th>Numero</th>
			<th>Oggetto</th>
			<th>Capienza</th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (MagazzinoDTO m : list) {
		%>
		<tr>
			<td><%=m.getId()%></td>
			<td><%=m.getIdOggetto()%></td>
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
      <label for="idoggetto">Oggetto</label>
    </div>
    <div class="col-75">
      <input type="text" id="idoggetto" name="capienza" placeholder="inserisci capienza">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="capienza">Capienza</label>
    </div>
    <div class="col-75">
      <input type="text" id="capienza" name="capienza" placeholder="inserisci capienza">
    </div>
  </div>
      <button type="submit" >Insert</button>
</form>

</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>