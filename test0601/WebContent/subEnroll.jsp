<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Subject 등록</h3>
	<h3>${error }</h3>
	<form action="subEnroll.do" method="post">
		ID:<input type="text" name="id"><br>
		TITLE:<input type="text" name="title"><br>
		# of Student:<input type="text" name="cnt"><br>
		<input type="submit" value="Enroll"><br>
	</form>
	<p>
	<a href="/test0601/proMenu.jsp">Menu</a>
</body>
</html>