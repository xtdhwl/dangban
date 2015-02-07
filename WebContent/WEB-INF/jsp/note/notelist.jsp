<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="net.shenru.mould.Note"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志列表</title>
</head>
<body>
	<%
		List<Note> list = (List<Note>) request.getAttribute("notelist");
	%>
	<p>日志列表</p>
	<ol>
		<%
			for (int i = 0; i < list.size(); i++) {
				Note note = list.get(i);
		%>
		<li><a
			href="<%out.write(request.getContextPath());%>/note?id=<%out.print(note.getId());%>">
				<%
					out.write(note.getTitle());
				%> </a>
		</li>
		<%
			}
		%>
	</ol>
</body>
</html>