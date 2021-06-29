<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="reservation.domain.*" %>
<%@ page import="reservation.service.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>reservationStatus</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<style>
table.type10 {
  border-collapse: collapse;
  text-align: center;
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
  width: 150px;
  padding: 10px;
  vertical-align: top;
}
table.type10 .even {
  background: #fdf3f5;
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
select {
	display:inline;
	vertical-align:middle;
    height:40px;
    width:150px;
    border-radius: 4px;
    font-size: 20px;
}
</style>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	ReservationService reservationService = ReservationServiceImpl.getInstance();
	
	String date = request.getParameter("key_date");
	String strroom = request.getParameter("key_room");
	String name = request.getParameter("key_name");
	String addr = request.getParameter("key_addr");
	String tel = request.getParameter("key_tel");
	String name_money = request.getParameter("key_name_money");
	String memo = request.getParameter("key_memo");
	String strMaxDay = request.getParameter("key_maxday");
	int maxDay = reservationService.getMaxDay(strMaxDay);
	
	reservationService.isAvailCreate(date, strroom, name, addr, tel, name_money, memo);
%>
	<form action='./reservationStatus.jsp' method='post'>
		<h1>
			# 예약상황입니다.
			<span style="text-align:right;">
			<select name='key_maxday'>
<%
	for (int i = 1; i <= ConstantValue.maxDay; i++) {
%>
				<option value='<%=i%>'><%=i%></option>
<%
	}
%>
			</select>
			<button type="submit" formmethod="POST">선택</button>
			</span>
		</h1>
	</form>
	
	<table class="type10">
		<thead>
		<tr>
			<th>일자</th>
			<th>Suite</th>
			<th>Superior</th>
			<th>Standard</th>
		</tr>
		</thead>
		<tbody>
<%
	for (int i = 0; i < maxDay; i++) {
		String reservDate = reservationService.getDate(i)[0];
		String reservDay = reservationService.getDate(i)[1];
		String isAvailSuite = reservationService.checkAvail(reservDate, 1);
		String isAvailSuperior = reservationService.checkAvail(reservDate, 2);
		String isAvailStandard = reservationService.checkAvail(reservDate, 3);
		
		if (i % 2 == 0) {
%>
		<tr>
			<th><%= reservDate %><%= reservDay%></th>
			<td><%= isAvailSuite %></td>
			<td><%= isAvailSuperior %></td>
			<td><%= isAvailStandard %></td>
		</tr>
<%
		} else {
%>
		<tr class="even">
			<th><%= reservDate %><%= reservDay%></th>
			<td><%= isAvailSuite %></td>
			<td><%= isAvailSuperior %></td>
			<td><%= isAvailStandard %></td>
		</tr>
<%
		}
	}
%>	
		</tbody>
	</table>
</body>
</html>