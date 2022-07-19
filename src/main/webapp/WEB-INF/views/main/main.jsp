<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="resources/myLib/jquery-3.2.1.min.js"></script>
<meta charset="UTF-8">
<title>** Music Story **</title>
<style>
	*{margin:0;padding:0;}
	body { margin:0 auto;}
	
	/* header  */
	header {
		position:fixed;
		display:flex;
		justify-content:space-between;
		align-items:center;
		top:0;left:0;
		width:100%;
		height:10%;
		color:white;
		background-color:#123456;
		z-index:100;
	}
			
	header h1 { 
		margin:20px;
		cursor:pointer;
	}
	
	#title {
		text-decoration: none;
	}
	
	.nav_list a{
		text-decoration:none;
	}
			
	header ul {
		list-style:none;
		margin: 0px 30px 0px 0px;
	}
	header ul li { 
	 	font-size:2rem;			
	 	font-weight:bold;
		float:left;
		margin: 20px;
	}

	header ul li:hover {
		font-size:2.2rem;
		background-color:#87CEFA;
		color:#123456;
		cursor:pointer;
	}
	
	
	/* main */
	main{
		width:100%;
		height:100%;
		position:relative;
	}
	
	main::after{
		width:100%;
		height:100%;
		content:"";
		background-image: url("resources/images/main_background.jpg");
		opacity: 0.6;
		position:absolute;
		top:0; left:0;
		z-index:-1;
	}
	
	/* Main 게시판 투명도 적용*/
	
	/* 본문 scroll */
	.inner-container {
	  	display: flex;
	}
	
	.carousel-container {
		width: 100%;
		height: 900px;
		display: flex;
		overflow: auto;
	}
	
	.carousel-container.y-scroll {
		flex-direction: column;
		scroll-snap-type: y mandatory;
	}
	
	.carousel-container::-webkit-scrollbar {
	  	display: none;
	}
	
	.item {
	    width: 100%;
	    height: 100%;
	    font-size: 30px;
	    display: flex;
	    flex: none;
	    justify-content: center;
	    align-items: center;
	    scroll-snap-align: start;
	    color: #eeeeee;
	    flex-direction: column;
	}

	
	/* 본문 1 로그인창 */
	
	.login-form {
		position:relative;
		display:flex;
		align-items:center;
		text-align: center;
	    flex-direction: column;
	    width: 500px;
	    height:500px;
	    background-image:url("resources/images/login.png");
	  	background-repeat:no-repeat;
	  	background-size:contain;
	    margin-right: auto;
	    margin-left: auto;
	    margin-top: 150px;
	    padding: 20px;
	    border: none;
	    border-radius: 250px;
    }
    
    .login-box{
	    position: absolute;
	    top:70px;
    }
    
    .text-field {
        font-size: 14px;
        padding: 10px;
        border: none;
        width: 250px;
        margin-bottom: 10px;
        color:black;
        border-radius: 20px;
 	 }
 
	.submit-btn {
	 	font-size: 14px;
	 	border: none;
	  	padding: 10px;
	  	width: 250px;
	  	background-color: #123456;
	 	margin-bottom: 30px;
	 	color: #ffffff;
	 	border-radius: 20px;
	 }
	 
	.join {
	  	position: absolute;
	  	left:190px; top:170px;
      	text-align: center;
	 }
	 
	.join a {
	  	font-size: 2rem;
	  	color: #FFFAFA;
	}
	
	/* Main 2 table  */
	table {
		border:5px solid black;
		color: #abcedf;
	}	
	
	th {
		text-align: center;
	}
	tr td{
		text-align:center;
	}	
	
		
	caption { 
	  display: table-caption;
	  text-align: center;
	  color: white;
	}
	.new_write a { 
		text-decoration:none; 
		color:#abcedf;
		}

	/* footer */
	footer {
	  	position: fixed;
	  	bottom: 0; left: 0;	
	  	width: 100%;
	  	height: 15%;
	  	background-color: #ddd;
	}
	
	marquee{
		display:flex;
		width:100%;
		height:150px;
		font-family:monospace;
		color:white;
		align-items:center;/* display: flex 이후 세로 중앙정렬  */
	}
	

	
</style>
</head>	
<body>

<header>
	<h1><a href="main" id="title"> Music Story </a></h1>
		<nav>
			<ul>
				<li class="nav_list"><a href="#item_1">사연게시판</a></li>
				<li class="nav_list"><a href="#item_2">자유게시판</a></li>
				<li class="nav_list"><a href="#item_3">공지사항</a></li>
			</ul>
		</nav>
</header>

<main>
	<div class="inner-container">
		<div class="carousel-container y-scroll">
		  <div class="item"	id= item>
		  	 <div class="login-form">
		  	 	<div class="login-box">
				    <form>
				      <input type="text" name="email" class="text-field" placeholder="아이디"><br>
				      <input type="password" name="password" class="text-field" placeholder="비밀번호">
				      <input type="submit" value="로그인" class="submit-btn">
				    </form>
				    <div class="join">
				      <a href="joinus">*회*원*가*입*</a>
				    </div>
			    </div><!-- login-box  -->
		 	  </div><!-- login-form  -->
		  </div><!-- item -->
		      <div class="item" id="item_1">
		      	<table width=80%>
		      		<caption>사연게시판</caption>
		      		<tr height="30" bgcolor="#123456">
		      		<th>번호</th><th>제목</th><th>작성자</th><th>조회수</th><th>추천수</th></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>1</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>2</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>3</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>4</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>5</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>6</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>7</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>8</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>9</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>10</td></tr>
		      	</table>
		      </div><!-- 사연게시판  -->
		      
		      <div class="item" id="item_2">
		      	<table width=80%>
		      		<caption>자유게시판</caption>
		      		<tr height="30" bgcolor="#123456">
		      		<th>번호</th><th>제목</th><th>작성자</th><th>조회수</th><th>추천수</th></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>1</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>2</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>3</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>4</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>5</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>6</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>7</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>8</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>9</td></tr>
		      		<tr height="30" bgcolor="#ffffff"><td>10</td></tr>
		      	</table>
			      	<div class="new_write">
			      		<a href="write">글쓰기</a>
			      	</div>
		      	</div><!-- 자유게시판  -->
		      <div class="item" id="item_3">
		      	<table width=80%>
		      		<caption>공지사항</caption>
		      		<tr height="30" bgcolor="#123456" align="middle" >
		      		<th>번호</th><th>제목</th><th>작성자</th><th>조회수</th></tr>
		      		<tr height="30"><td>10</td><td>최종업데이트 3.0ver</td><td>관리자</td><td>5</td></tr>
		      		<tr height="30"><td>9</td><td>최종업데이트 2.8ver</td><td>관리자</td><td>5</td></tr>
		      		<tr height="30"><td>8</td><td>최종업데이트 2.6ver</td><td>관리자</td><td>5</td></tr>
		      		<tr height="30"><td>7</td><td>최종업데이트 2.4ver</td><td>관리자</td><td>5</td></tr>
		      		<tr height="30"><td>6</td><td>최종업데이트 2.2ver</td><td>관리자</td><td>5</td></tr>
		      		<tr height="30"><td>5</td><td>최종업데이트 2.0ver</td><td>관리자</td><td>5</td></tr>
		      		<tr height="30"><td>4</td><td>최종업데이트 1.8ver</td><td>관리자</td><td>5</td></tr>
		      		<tr height="30"><td>3</td><td>최종업데이트 1.4ver</td><td>관리자</td><td>5</td></tr>
		      		<tr height="30"><td>2</td><td>최종업데이트 1.2ver</td><td>관리자</td><td>5</td></tr>
		      		<tr height="30"><td>1</td><td>초기업데이트 1.0ver</td><td>관리자</td><td>5</td></tr>
		      	</table>
		      </div><!-- 공지사항 -->
		      
	    </div><!--carousel-container y-scroll  -->
	</div> <!-- inner-container -->
</main>

<footer>
	<marquee width="100%" direction="left" loop="-1" scrolldelay="10" bgcolor="dimgray">
		<span id="footer_m">
			<image src="resources/images/marquee1.jpg">
			<image src="resources/images/marquee2.jpg">
			<image src="resources/images/marquee3.jpg">
			<image src="resources/images/marquee4.jpg">
			<image src="resources/images/marquee5.jpg"> 
			<image src="resources/images/marquee6.jpg"> 
			<image src="resources/images/marquee7.jpg"> 
			<image src="resources/images/marquee8.jpg"> 
			<image src="resources/images/marquee9.jpg"> 
			<image src="resources/images/marquee10.jpg"> 
		</span>
	</marquee>
</footer>
</body>
</html>