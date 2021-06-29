<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="board2.domain.*" %>
<%@ page import="board2.service.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>updateComplete</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="./board.css?after">
</head>
<body>
<%
	BoardItemService boardItemService = BoardItemServiceImpl.getInstance();
	
	request.setCharacterEncoding("utf-8");
	int itemid = Integer.parseInt(request.getParameter("key_itemid"));
	String title = request.getParameter("key_title");
	String content = request.getParameter("key_content");
	int boardid = Integer.parseInt(request.getParameter("key_boardid"));
	
	boardItemService.update(boardItemService.selectOne(itemid), title, content);
%>
<h1 id='head'># 수정 완료되었습니다.</h1>
<form action="./boardItem.jsp" method="post">
   	<input type="hidden" name="key_boardid" value='<%= boardid %>'>
   	<button class='btn-1' type="submit" formmethod="POST">목록으로</button>
</form>
</body>
</html>