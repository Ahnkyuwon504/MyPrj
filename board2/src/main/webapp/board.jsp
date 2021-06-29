<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="board2.domain.*" %>
<%@ page import="board2.service.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>board</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="./board.css?after">
</head>
<body>
	<h1 id='head'># 게시판 목록</h1>
<%
	BoardService boardService = BoardServiceImpl.getInstance();

	for (Board board : boardService.selectAll()) {
%>
	<form action="./boardItem.jsp" method="post">
    	<input type="hidden" name="key_boardid" value='<%= board.getBoardid() %>'>
    	<button class='btn-1' type="submit" formmethod="POST"><%= board.getTitle() %></button>
	</form>
<%		
	}
%>
</body>
</html>








