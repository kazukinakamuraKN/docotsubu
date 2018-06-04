<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.Mutter,java.util.List" %>
<!-- get youser information where save sessionScope -->
<%
User loginUser = (User) session.getAttribute("loginUser");
%>
<!-- get mutterList where save applicationScope -->
<% List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList"); %>
<!-- get errorMessage save requestScope -->
<% String errorMsg = (String) request.getAttribute("errorMsg"); %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>docotsubu</title>
</head>
<body>
	<h1>docotsubuMain</h1>
	<p>
	<%= loginUser.getName() %> are login now
	<a href="/docoTsubu/Logout">logout?</a>
	</p>
	<p><a href="/docoTsubu/Main">update?</a></p>
	<form action="/docoTsubu/Main" method="post">
		<input type="text" name="text">
		<input type="submit" value="Mutter">
	</form>
	<% if(errorMsg != null) {%>
	<p><%= errorMsg %></p>
	<% } %>
	<% for(Mutter mutter : mutterList) {%>
		<p><%= mutter.getUserName() %>:<%= mutter.getText() %></p>
	<% } %>
</body>
</html>