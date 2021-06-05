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
ArrayList<SubjectVO> list2 = (ArrayList<SubjectVO>)request.getAttribute("list2");
SubjectVO m = list2.get(0);
%>
<h1><%= m.getName() %> 총 수강인원 : <%= m.getCount() %>명</h1>

<%
	ArrayList<SubjectVO> list = (ArrayList<SubjectVO>)request.getAttribute("list");
	if(!list.isEmpty())
	{
%>
<table border="1">
	<tr><th>id</th><th>이름</th></tr>
	<%
		for(int i=0;i<list.size();i++)
		{ 
			SubjectVO member = list.get(i); 
	%>
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
	<a href="/test0601/proMenu.jsp">Menu</a>
</body>
</html>