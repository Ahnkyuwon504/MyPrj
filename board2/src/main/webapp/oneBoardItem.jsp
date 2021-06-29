<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="board2.domain.*" %>
<%@ page import="board2.service.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>oneBoardItem</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
table.type10 {
  border-collapse: collapse;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  margin: 20px 10px;
}
table.type10 thead th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  color: #fff;
  background: #e7708d;
  margin: 20px 10px;
}
table.type10 tbody th {
  width: 150px;
  padding: 10px;
}
table.type10 td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
}
table.type11 {
  border-collapse: separate;
  border-spacing: 1px;
  text-align: center;
  line-height: 1.5;
  margin: 20px 10px;
}
table.type11 th {
  width: 155px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  color: #fff;
  background: #ce4869 ;
}
table.type11 td {
  width: 155px;
  padding: 10px;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
  background: #eee;
}
form {
	display:inline;
	vertical-align:middle;
}
button {
	display:inline;
	vertical-align:middle;
    background-color:#FF5A5F;
    color:white;
    width: 70px;
    height: 45px;
    font-size: 15px;
    font-weight: 700;
    border-radius: 6px;
    border: 0;
    outline: 0;
}
input[type=text]{
	display:inline;
	vertical-align:middle;
    height:40px;
    width:300px;
    border-radius: 4px;
    font-size: 20px;
}
</style>
</head>
<body>
<%
	BoardItemService boardItemService = BoardItemServiceImpl.getInstance();
	
	request.setCharacterEncoding("utf-8");
	int itemid = Integer.parseInt(request.getParameter("key_itemid"));
	int count = Integer.parseInt(request.getParameter("key_count"));
	int boardid = Integer.parseInt(request.getParameter("key_boardid"));
	
	BoardItem boardItem = boardItemService.selectOne(itemid);
	boardItem.setComments(boardItemService.getAllComments(boardItem));
	
	String commentTitle = request.getParameter("key_commentTitle");
	String commentDate = request.getParameter("key_commentDate");
	String commentContent = request.getParameter("key_commentContent");
	
	if (commentTitle != null) {
		BoardItem newComment = new BoardItem(commentTitle, commentDate, commentContent, boardid);
		boardItemService.createComment(boardItem, newComment);
	}
%>
<h1 id='head'># '<%= boardItem.getTitle() %>' 게시물입니다.</h1>
<form id='updateBoardItem' action='./updateComplete.jsp' method='post' accept-charset="utf-8">
	<table class="type11">
		<tr>
			<th>번호</th>
			<td>
				<input type='text' value='<%= count %>' readonly>
			</td>
		<tr>
		<tr>
			<th>제목</th>
			<td>
				<input type='text' name='key_title' value='<%= boardItem.getTitle() %>'/>
			</td>
		<tr>
		<tr>
			<th>일자</th>
			<td>
				<input type='text' value='<%= boardItemService.getDate() %>' readonly>
			</td>
		<tr>
		<tr>
			<th>내용</th>
			<td>
				<input type='text' name='key_content' value='<%= boardItem.getContent() %>'/>
			</td>
		<tr>
	</table>
	<input type="hidden" name="key_itemid" value='<%= itemid %>'>
	<input type="hidden" name="key_boardid" value='<%= boardid %>'>
</form>
<h1 id='head'># 해당 게시글에 대한 댓글입니다.</h1>
<form id='insertComment' action='./oneBoardItem.jsp' method='post' accept-charset="utf-8">
	<table class="type10">
	<thead>
		<tr>
			<th>제목</th>
			<th>일자</th>
			<th>내용</th>
		<tr>
	</thead>
	<tbody>
<%
	for (BoardItem comment : boardItem.getComments()) {
%>
		<tr>
			<td><%= comment.getTitle() %></td>
			<td><%= comment.getDate() %></td>
			<td><%= comment.getContent() %></td>
		<tr>
<%
	}
%>
		<tr>
			<td>
				<input type='text' name='key_commentTitle'>
			</td>
			<td>
				<input type='text' name='key_commentDate' value='<%= boardItemService.getDate() %>' readonly>
			</td>
			<td>
				<input type='text' name='key_commentContent'>
			</td>
		<tr>
	</tbody>
	</table>
	 <input type="hidden" name="key_itemid" value='<%= itemid %>'>
	 <input type="hidden" name="key_count" value='<%= count %>'>
	 <input type="hidden" name="key_boardid" value='<%= boardid %>'>
</form>

<div style="text-align:center;">
	<form action="./boardItem.jsp" method="post">
	   	<input type="hidden" name="key_boardid" value='<%= boardid %>'>
	   	<button class='btn-1' type="submit" formmethod="POST">목록</button>
	</form>
	<button onclick='javascript:$("#insertComment").submit()'>추가</button>
	<form action="./deleteComplete.jsp" method="post">
		<input type="hidden" name="key_itemid" value='<%= itemid %>'>
	   	<input type="hidden" name="key_boardid" value='<%= boardid %>'>
	   	<button class='btn-1' type="submit" formmethod="POST">삭제</button>
	</form>
	<button class='btn-1' onclick='javascript:$("#updateBoardItem").submit()'>수정</button>
</div>
</body>
</html>








