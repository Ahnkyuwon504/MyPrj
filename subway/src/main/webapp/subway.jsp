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
<style>
body {
  background: #E2E2E2;
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
	int total = 0;
%>
	<h2><img src="./subwayNoBG.png" width="46px" height="46px">&nbsp; 지하철 안내 시스템입니다.</h2> 
	<form action="./subway.jsp" method="post" accept-charset="utf-8">
	   	<input type="text" placeholder=" 출발역을 입력해주세요." name="key_start">
	   	<input type="text" placeholder=" 도착역을 입력해주세요." name="key_arrive">
	   	<button type="submit" formmethod="POST">검 색</button>
	</form>
	
	
	
	<section style="margin-top:30px">
		 <span class="circle" style="position: absolute; margin-top:3px; left:10px; z-index: 2;">노량진</span>
		 <span style="background-color: orange; position: absolute; left:60px; color: white; z-index: 1;">9호선</span>
		 <span class="circle" style="position: absolute; margin-top:3px; left:220px; z-index: 2;">시청</span>
		 <span style="background-color: green; position: absolute; left:270px; color: white; z-index: 1;">2호선</span>
		 <span class="circle" style="position: absolute; margin-top:3px; left:430px; z-index: 2;">고속터미널</span>
	</section>
	
	
	
	
	
	
	<h1>출발합니다.</h1>
<%
	for (int i = 0; i < lineAndTime.size(); i++) {
		String stationName = subwayServiceImpl.getStationName(lineAndTime.get(i)[0]);
%>
	<h1>
		<%= stationName %>을 타고 <%= route.get(i) %> 역에서 <%= route.get(i+1) %> 역으로 이동합니다.
		소요시간은 약 <%= lineAndTime.get(i)[1] %>분 입니다.
	</h1> 
<%
		total += lineAndTime.get(i)[1];
	}
%>
	<h1> <%= route.get(route.size() - 1) %> 역에 도착했습니다.</h1>
	<h1> 총 소요시간은 <%= total %>분 입니다.</h1>
</body>
</html>