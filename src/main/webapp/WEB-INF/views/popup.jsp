<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
	//쿠키설정    
	function setCookie( name, value, expiredays ) {
	var todayDate = new Date();
	todayDate.setDate( todayDate.getDate() + expiredays );
	document.cookie = name + '=' + escape( value ) + '; path=/; expires=' + todayDate.toGMTString() + ';'
	}
	
	//쿠키 불러오기
	function getCookie(name) 
	{ 
	    var obj = name + "="; 
	    var x = 0; 
	    while ( x <= document.cookie.length ) 
	    { 
	        var y = (x+obj.length); 
	        if ( document.cookie.substring( x, y ) == obj ) 
	        { 
	            if ((endOfCookie=document.cookie.indexOf( ";", y )) == -1 ) 
	                endOfCookie = document.cookie.length;
	            return unescape( document.cookie.substring( y, endOfCookie ) ); 
	        } 
	        x = document.cookie.indexOf( " ", x ) + 1; 
	        
	        if ( x == 0 ) break; 
	    } 
	    return ""; 
	}
	
	//닫기 버튼 클릭시
	function closeWin(key)
	{
	    if($("#todaycloseyn").prop("checked"))
	    {
	        setCookie('divpop'+key, 'Y' , 1 );
	    }
	    self.close();
	}
	
	$(function(){    
	    if(getCookie("divpop1") !="Y"){
	        $("#divpop1").show();
	    }
	});
</script>
<title>~운영자 추천 노래~</title>
<style type="text/css">
	* 	{margin:0; padding:0;}
	img {float:left;}
	#profile { float:right;}
	#ex {display:inline-block;}
	p 	{ font-size:18px;}
	input {width:75px; height:25px;
			background-color:black;
			color:white;
			border: none;}
	input:hover { cursor:pointer;}		
	#footer { 
			position:absolute;
			bottom:0;
			left:10px;
			display:flex;
			width:100%;
			height:5%;
			background-color:black ;
			color:white;
			}		
	a { 
		position:absolute;
		right:0;
		color:white;
		}		
</style>
</head>
<body>
<form name="notice_form">
	<img src="resources/images/popup.jpg" alt="이달의 운영자픽 노래"  usemap="#pop"/>
	<p id="profile">
	<b>출생</b><br>
	1994년 4월 26일 (28세)<br>
	<b>소속사</b><br>
	주식회사 레시피뮤직</p><br>
	<div id="ex">대한민국의 가수. 활동명은 주시크(Joosiq)로 2020년 싱글 앨범 [불 좀 꺼줄래]로 데뷔한 싱어송라이터이다.
		 주관적해석으로 전 연인이 이 노래를 듣고 내 마음이 이 정도 였다는 것을 노래로 표현</div>
    <div id="divpop1" class="divpop"> 
	  <div id="footer">
		<input type='checkbox' name='chkbox' id='todaycloseyn' value='Y'>
		오늘 하루 이 창을 열지 않음
		<a href='#' onclick="javascript:closeWin(1);"><B>[닫기]</B></a>
	  </div> <!-- footer  -->
    </div>	
</form>
</body>
</html>