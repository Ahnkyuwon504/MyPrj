<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="subway.domain.*" %>
<%@ page import="subway.service.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>subway</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="./subway.css?after">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<style>
body {
  background: #E2E2E2;
}
.line {
	text-align: center;
    width: 100%;
    height: 2px;
    border: 0;
    outline: 0;
    background-color: black;
}

span {
	padding: 40px 1px;
	text-align: center;
	display:inline-block;
	color:white;
    width: 210px;
    height: 20px;
    font-size: 15px;
    font-weight: 700;
    border-radius: 6px;
    border: 0;
    outline: 0;
}
.circle {
	color: black;
	width: 100px;
	height: 15px;
	border-radius:50%;
	background: white;
	text-align:center;
	font-size:10px;
}
</style>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	SubwayServiceImpl subwayServiceImpl = SubwayServiceImpl.getInstance();
	
	//subwayServiceImpl.deleteDB();
	//subwayServiceImpl.createDB();
	//subwayServiceImpl.insertDB();
	
	String start = request.getParameter("key_start");
	String arrive = request.getParameter("key_arrive");
	
	if (start == null) {
		start = "용산";
		arrive = "회기";
	} 
	
	Subway subway = subwayServiceImpl.create();
	Print print = subwayServiceImpl.getTime(subway, start, arrive);
	
	ArrayList<String> route = print.getRoute();
	ArrayList<int[]> lineAndTime = subwayServiceImpl.getLineAndTime(route);
	
%>
	<div><img src="./logo2RM.png">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="./logo.png"></div>
	<div class="line"></div>
	<h2><i class="fas fa-subway"></i> 지하철 경로 탐색 시스템입니다.</h2> 
	<form action="./subway.jsp" method="post" accept-charset="utf-8">
	   	<input type="text" placeholder="<%=start %>" name="key_start">
	   	<input type="text" placeholder="<%=arrive %>" name="key_arrive">
	   	<button type="submit" formmethod="POST">검 색</button>
	</form>
	
	<h2><i class="fas fa-subway"></i> 환승사항입니다.</h2>
	<section style="margin-top:30px">
<%
	int howMany = 0;
	for (int i = 0; i < route.size(); i++) {
		if (i == 0) {
%>
		<span class="circle" style="position: absolute; margin-top:3px; left:10px; z-index: 2;"><%= route.get(i) %></span>
		<span style="background-color: <%= subwayServiceImpl.getLineColor(lineAndTime.get(i)[0]) %>; position: absolute; left:60px; color: white; z-index: 1;"><%= subwayServiceImpl.getStationName(lineAndTime.get(i)[0]) %></span>
<%			
			continue;
		} else if (i == route.size() - 1) {
			howMany++;
%>
		<span class="circle" style="position: absolute; margin-top:3px; left:<%= 10 + 210*howMany %>px; z-index: 2;"><%= route.get(i) %></span>
<%
			continue;
		}
		if (lineAndTime.get(i)[0] != lineAndTime.get(i - 1)[0]) {
			howMany++;
%>
		 <span class="circle" style="position: absolute; margin-top:3px; left:<%= 10 + 210*howMany %>px; z-index: 2;"><%= route.get(i) %></span>
		 <span style="background-color: <%= subwayServiceImpl.getLineColor(lineAndTime.get(i)[0]) %>; position: absolute; left:<%= 60 + 210*howMany %>px; color: white; z-index: 1;"><%= subwayServiceImpl.getStationName(lineAndTime.get(i)[0]) %></span>
<%
		} 
	}

%>
	</section>
	<br><br><br><br><br>
	<h2><i class="fas fa-subway"></i> 총 경로</h2>
	<h3>
<%
	int total = 0;
	for (int i = 0; i < lineAndTime.size(); i++) {
		String stationName = subwayServiceImpl.getStationName(lineAndTime.get(i)[0]);
		total += lineAndTime.get(i)[1];
		
		if (i == 0) {
%>
		<%= route.get(i) %>역-
<%		
		continue;
		}
		if (i%5 == 0) {
%>
		<br><br>
<%
		}
		if (i == lineAndTime.size() - 1) {
%>
		<%= route.get(i) %>역(<%= total - lineAndTime.get(i - 1)[1] %>m)-<%= route.get(i+1) %>역(<%= total %>m)
<%
		continue;
		}
%>
		<%= route.get(i) %>역(<%= total - lineAndTime.get(i - 1)[1] %>m)-
<%
	}
%>
	</h3>
	<h2><i class="fas fa-subway"></i> 총 소요시간은 <%= total %>분 입니다.</h2>
</body>
</html>