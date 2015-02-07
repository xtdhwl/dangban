<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>写笔记</title>
<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor/ueditor.all.js"></script>
</head>
<body>
	<%
		Object idObj = request.getAttribute("id");
		Object titleObj = request.getAttribute("title");
		Object contentObj = request.getAttribute("content");

		String action = request.getAttribute("action").toString();
		String id = "";
		String title = "";
		String content = "";
		if (idObj != null) {
			id = idObj.toString();
		}
		if (titleObj != null) {
			title = titleObj.toString();
		}
		if (contentObj != null) {
			content = contentObj.toString();
		}
	%>
	<form
		action="<%=request.getContextPath()%>/NoteAction?id=<%=id%>&action=
		<%=action%>"
		method="post">
		<table>
			<tr>
				<td><input type="text" name="title" style="width: 610px"
					value="<%=title%>">
				</td>
			</tr>
			<tr>
				<td><textarea name="content" id="myEditor"><%=content%></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
    var editor = new UE.ui.Editor();
    editor.render("myEditor");
    //1.2.4以后可以使用一下代码实例化编辑器
    //UE.getEditor('myEditor')
</script>
</body>
</html>