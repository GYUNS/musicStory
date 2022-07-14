<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>MusicStory</title>
<script src="https://kit.fontawesome.com/9d68581f8f.js" crossorigin="anonymous"></script>
<style>
	*{
		cursor: none !important
	}
	body{
		background-color: 	#e6f2ff;
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
		color:#004d99;
	}
	
	.wrap a figcaption{
		text-decoration: none;
	}
	
	figcaption:hover {
		cursor:pointer;
	}
	
	/* 배경화면 변화 */
	
 	@keyframes bodycolor {
	0%  { background-color: #e6f2ff; }   
	25% { background-color: #cce6ff; }
	50% { background-color: #99ccff; } 
	75% { background-color: #cce6ff; } 
	0%  { background-color: #e6f2ff; } 
	}
	
	body {
		animation-name: bodycolor;
		animation-duration: 5s;
		animation-iteration-count : infinite ;
	}  
	
	/* 마우스 이벤트 */
	.circle {
    position: absolute;
    top: 0;
    left: 0;
    width: 64px;
    height: 64px;
    background-image:url("resources/images/mouse.png");
    transform: translate(-50%, -50%);
    z-index:9999;
 	opacity : 1;
 	pointer-events:none
	}
	
	</style>
</head>
<body>

	<!-- 마우스  -->
	<div class="circle"></div>

	<div class="wrap">
		<a href="main"><img width="350px" height="200px" alt="LP판" 
		src="resources/images/disc-jockey-1293238_640.png"></a>
		<figcaption>음악 공유하러 클릭하기!</figcaption>
	</div>
	
</body>
<script>
/* 팝업창 */
	window.open("popup","naver","width=450px, height=450px, left=200px, top=20px, scrollbars=no, toolbar=no, location=no");

/* 마우스 이벤트  */
	const circle = document.querySelector(".circle");

	document.addEventListener("mousemove", (e) => {
	const mouseX = e.clientX;
    const mouseY = e.clientY;
    circle.style.left = mouseX + 'px';
    circle.style.top = mouseY + 'px';
	});


</script>
</html>
