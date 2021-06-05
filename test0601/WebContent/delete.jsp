<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="login.vo.SubjectVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Subject</h1>
	${error }
	<form action="search.do" method="post">
		ID:<input type="text" name="id">
		<input type="hidden" name="tp" value="delete">
		<input type="submit" value="Search">
	</form>
	<p>
	<%
	SubjectVO member = (SubjectVO)request.getAttribute("member");
	if(member != null) {
	%>
	<form action="delete.do" method="post">
		${member.id } / ${member.name }
		<input type="hidden" name="uid" value="${member.id }">
		<input type="hidden" name="cnt" value="${member.count }">
		<input type="submit" value="Delete">
	</form>
	<% }else{ %>
		${result}
	<% } %>
	<p>
	<a href="/test0601/stuMenu.jsp">Menu</a>
</body>
</html>