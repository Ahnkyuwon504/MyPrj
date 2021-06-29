<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="reservation.domain.*" %>
<%@ page import="reservation.service.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>reserve</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
button {
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
table.type11 {
  border-collapse: separate;
  border-spacing: 1px;
  text-align: center;
  line-height: 1.5;
  margin: 20px 10px;
}
table.type11 th {
  width: 100px;
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
</style>
</head>
<body>
<h3>&nbsp;&nbsp;&nbsp;&nbsp;# 상세 예약페이지입니다.</h3>

<%
	request.setCharacterEncoding("utf-8");
	ReservationService reservationService = ReservationServiceImpl.getInstance();
	
	String date = request.getParameter("key_date");
	int room = Integer.parseInt(request.getParameter("key_room"));
	String whatRoom = reservationService.roomName(room);
%>
	<form action='./reservationStatus.jsp' method='post' accept-charset="utf-8">
		<table class="type11">
			<tr>
				<th width=50>성명</th>
				<td width=50><input name='key_name' type='text'></td>
			</tr>
			<tr>
				<th width=50>예약일자</th>
				<td width=50><input name='key_date' type='text' value='<%= date %>' readonly></td>
			</tr>
			<tr>
				<th width=50>예약방</th>
				<td width=50><input type='text' value='<%= whatRoom %>' readonly></td>
			</tr>
			<tr>
				<th width=50>주소</th>
				<td width=50><input name='key_addr' type='text'></td>
			</tr>
			<tr>
				<th width=50>전화번호</th>
				<td width=50><input name='key_tel' type='text'></td>
			</tr>
			<tr>
				<th width=50>입금자명</th>
				<td width=50><input  name='key_name_money' type='text'></td>
			</tr>
			<tr>
				<th width=50>남기실말</th>
				<td width=50><input  name='key_memo' type='text'></td>
			</tr>
		</table>
		<input name='key_room' type='hidden' value='<%= room %>'>
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<button type="submit" formmethod="POST">예약</button>
	</form>
</body>
</html>








