<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="login.vo.SubjectVO" %>
<%@ page import ="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	ArrayList<SubjectVO> list = (ArrayList<SubjectVO>)request.getAttribute("list");
	if(!list.isEmpty())
	{
%>
<table border="1">
	<tr><th>과목번호</th><th>과목명</th></tr>
	<%
		for(int i=0;i<list.size();i++)
		{ 
			SubjectVO member = list.get(i); %> 
	<tr>
		<td><%= member.getId() %></td>
		<td><%= member.getName() %></td>
	</tr>
	<%} 	
	}else{
		out.print("<h3>none</h3>");
	}
	%>
</table>
<p>
	<a href="/test0601/stuMenu.jsp">Menu</a>
</body>
</html>