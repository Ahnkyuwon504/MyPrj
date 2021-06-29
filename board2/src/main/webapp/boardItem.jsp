<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="board2.domain.*" %>
<%@ page import="board2.service.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>boardItem</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
table.type10 .even {
  background: #fdf3f5;
}
ul {
	text-align:center;
}
ul li {
	display:inline;
	vertical-align:middle;
}
ul li a {
	display:-moz-inline-stack;	/*FF2*/
	display:inline-block;
	vertical-align:top;
	padding:4px;
	margin-right:3px;
	width:15px !important;
	color:#000;
	font:bold 12px tahoma;
	border:1px solid #eee;
	text-align:center;
	text-decoration:none;
	width /**/:26px;	/*IE 5.5*/

}
ul li a.now {
	color:#fff;
	background-color:#f40;
	border:1px solid #f40;
}
ul li a:hover, ul li a:focus {
	color:#fff;
	border:1px solid #f40;
	background-color:#f40;
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
<h1 id='head'># 게시판</h1>
	<table class="type10">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>일자</th>
			</tr>
		</thead>
		<tbody>
<%
	request.setCharacterEncoding("utf-8");
	BoardItemService boardItemService = BoardItemServiceImpl.getInstance();
	int onePage = ConstantValue.onePage;
	
	String search = request.getParameter("key_search");
	int boardid = Integer.parseInt(request.getParameter("key_boardid"));
	String strfrom = request.getParameter("from");
	String strto = request.getParameter("to");
	
	int boardItemHowMany = boardItemService.selectAll(boardid).size();
	String keyword = boardItemService.getKeyWord(search);
	
	int from = boardItemService.getFromAndTo(strfrom, strto, boardItemHowMany, onePage)[0];
	int to = boardItemService.getFromAndTo(strfrom, strto, boardItemHowMany, onePage)[1];
	
	int cnt = 1;
	for (BoardItem boardItem : boardItemService.selectAll(boardid, keyword)) {
		if (cnt < from) {
			cnt++;
			continue;
		}
		if (cnt % 2 == 0) {
%>
		<tr>
			<th class="even"><%= cnt %></th>
			<td class="even">
				<form id='oneBoardItem<%= boardItem.getItemid() %>' action='./oneBoardItem.jsp' method='post'>
					<input type='hidden' name='key_itemid' value='<%= boardItem.getItemid() %>'/>
					<input type='hidden' name='key_boardid' value='<%= boardid %>'/>
					<input type='hidden' name='key_count' value='<%= cnt %>'/>
				</form>
				<a href='javascript:$("#oneBoardItem<%= boardItem.getItemid() %>").submit()'><%= boardItem.getTitle() %></a>
			</td>
			<td class="even"><%= boardItem.getDate() %></td>
		</tr>
<%	
		} else {
%>
		<tr>
			<th><%= cnt %></th>
			<td>
				<form id='oneBoardItem<%= boardItem.getItemid() %>' action='./oneBoardItem.jsp' method='post'>
					<input type='hidden' name='key_itemid' value='<%= boardItem.getItemid() %>'/>
					<input type='hidden' name='key_boardid' value='<%= boardid %>'/>
					<input type='hidden' name='key_count' value='<%= cnt %>'/>
				</form>
				<a href='javascript:$("#oneBoardItem<%= boardItem.getItemid() %>").submit()'><%= boardItem.getTitle() %></a>
			</td>
			<td><%= boardItem.getDate() %></td>
		</tr>
<%
		}
		cnt++;
		if (cnt > to) {
			break;
		}
	}
%>
	</tbody>
	</table>
	
	
		<ul>
			<li><a href='boardItem.jsp?from=<%= from - onePage %>&to=<%= from - 1 %>&key_boardid=<%= boardid %>'>&lt;</a></li>
<%
	for (int i = 1; i <= boardItemService.selectAll(boardid, keyword).size() / onePage + 1; i++) {
%>
		<li><a href='boardItem.jsp?from=<%= (i-1)*onePage + 1 %>&to=<%= i*onePage %>&key_boardid=<%= boardid %>'><%= i %></a></li>
<%
	}
%>
		<li><a href='boardItem.jsp?from=<%= from + onePage %>&to=<%=from + 2*onePage - 1 %>&key_boardid=<%= boardid %>'>&gt;</a></li>
		</ul>
	<div style="text-align:center;">
		<form id='search' action='./boardItem.jsp' method='post' accept-charset="utf-8">
			<input type='text' name='key_search'>
			<input type="hidden" name="key_boardid" value='<%= boardid %>'>
			<button class='btn-1' onclick='javascript:$("#search").submit()'>검 색</button>
		</form>
		<form action="./insert.jsp" method="post">
		   	<input type="hidden" name="key_insert" value='<%= boardItemHowMany + 1 %>'>
		   	<input type="hidden" name="key_boardid" value='<%= boardid %>'>
		   	<button class='btn-1' type="submit" formmethod="POST">글 작성</button>
		</form>
		
		<form>
			<button class='btn-1' type="button" onclick="location.href='./board.jsp'">게시판 목록</button>
		</form>
	</div>
</body>
</html>