<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"%>
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
  <a class="active" href="UserServlet?mode=userlist">Users</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%MagazzinoDTO m = (UserDTO) request.getAttribute("dto");%>


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
      <button type="submit" >Edit</button>
</form>

	
</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>