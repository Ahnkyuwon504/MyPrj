<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="board2.domain.*" %>
<%@ page import="board2.service.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>insertComplete</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="./board.css?after">
</head>
<body>
<%
	BoardItemService boardItemService = BoardItemServiceImpl.getInstance();
	
	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("key_title");
	String date = request.getParameter("key_date");
	String content = request.getParameter("key_content");
	int boardid = Integer.parseInt(request.getParameter("key_boardid"));
	
	BoardItem boardItem = new BoardItem(title, date, content, boardid);
	boardItemService.create(boardItem);
%>
<h1 id='head'># 추가 완료되었습니다.</h1>
<form action="./boardItem.jsp" method="post">
   	<input type="hidden" name="key_boardid" value='<%= boardid %>'>
   	<button class='btn-1' type="submit" formmethod="POST">목록으로</button>
</form>
</body>
</html>