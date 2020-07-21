<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Admin</title>
<link href="css/vittoriostyle.css" rel="stylesheet">
</head>
<body>
<%@include file="css/header.jsp"%>

<div class="navbar">
  <a class="active" href="homeadmin.jsp">Home</a>
  <a class="active" href="codici.jsp">Mostra codici</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>

<div class="main">
<h1>Welcome ${user.getUsername()}</h1>

</div>

<%@ include file="css/footer.jsp" %>

</body>
</html>