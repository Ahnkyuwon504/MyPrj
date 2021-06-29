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
	<form action="./subway.jsp" method="post" accept-charset="utf-8">
	   	<input type="text" placeholder=" 출발역을 입력해주세요." name="key_start">
	   	<input type="text" placeholder=" 도착역을 입력해주세요." name="key_arrive">
	   	<button type="submit" formmethod="POST">검 색</button>
	</form>
	<h1>출발합니다.</h1>
<%
	for (int i = 0; i < lineAndTime.size(); i++) {
%>
	<h1>
		<%= lineAndTime.get(i)[0] %> 호선을 타고 <%= route.get(i) %> 역에서 <%= route.get(i+1) %> 역으로 이동합니다.
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