<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="board2.domain.*" %>
<%@ page import="board2.service.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>insert</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="./board.css?after">
</head>
<body>
<%
	BoardItemService boardItemService = BoardItemServiceImpl.getInstance();
	
	request.setCharacterEncoding("utf-8");
	int boardid = Integer.parseInt(request.getParameter("key_boardid"));
	int count = Integer.parseInt(request.getParameter("key_insert"));
%>
<h1 id='head'># 새 글 작성</h1>
<form id='insertBoardItem' action='./insertComplete.jsp' method='post' accept-charset="utf-8">
	<table>
		<tr>
			<td>번호</td>
			<td><input type='text' value='<%= count %>' readonly></td>
		<tr>
		<tr>
			<td>제목</td>
			<td><input type='text' name='key_title'/></td>
		<tr>
		<tr>
			<td>일자</td>
			<td><input type='text' name='key_date' value='<%= boardItemService.getDate() %>' readonly></td>
		<tr>
		<tr>
			<td>내용</td>
			<td><input type='text' name='key_content'/></td>
		<tr>
	</table>
	<input type="hidden" name="key_boardid" value='<%= boardid %>'>
</form>
<form action="./boardItem.jsp" method="post">
   	<input type="hidden" name="key_boardid" value='<%= boardid %>'>
   	<button class='btn-1' type="submit" formmethod="POST">취소</button>
</form>
<button class='btn-1' onclick='javascript:$("#insertBoardItem").submit()'>추가</button>
</body>
</html>








