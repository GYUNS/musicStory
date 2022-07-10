<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>MusicStory</title>
	<script src="https://kit.fontawesome.com/9d68581f8f.js" crossorigin="anonymous"></script>
	<style>
	body{
		background-color: 	#E0FFFF;
	}
	
	.wrap{
		position:absolute;
		top:50%;
		left:50%;
		transform:translate(-50%,-50%);
		text-align:center;
	}
	
	.wrap:hover{
		cursor:pointer;
	}
	
	
	figcaption {
		font-size: 25px;
		font-family: monospace;
		color:#4169E1;
	}
	
	.wrap a figcaption{
		text-decoration: none;
	}
	
	figcaption:hover {
		cursor:pointer;
		color:#87CEFA;
	}
	
	</style>
</head>
<body>
	<div class="wrap">
		<a href="main"><img width="300px" height="200px" alt="LP판" 
		src="resources/images/disc-jockey-1293238_640.png"></a>
		<figcaption>음악 공유하러 클릭하기!</figcaption>
	</div>
	
	
</body>
</html>
