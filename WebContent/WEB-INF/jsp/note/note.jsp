<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%
		out.write(request.getAttribute("title").toString());
	%></title>
<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor/ueditor.all.js"></script>
</head>
<body>


<b><font size="6"><%
		out.write(request.getAttribute("title").toString() + "\t\t");
	%></font>	</b>
	<a
		href="<%out.append(request.getContextPath());%>/noteedit?id=<%out.write(request.getAttribute("id").toString());%>">编辑</a>
	</br></br>

	<%
		out.write(request.getAttribute("content").toString());
	%>

</body>
</html>