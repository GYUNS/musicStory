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
		height:100px;
		color:#abcedf;
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
		text-decoration-line:none;
	}
	.nav_list {
		text-decoration-line:none;
	}
	
	/* main */
	main{
		background-image: url("resources/images/main_background.jpg");
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
 	 }
 
	.submit-btn {
	 	font-size: 14px;
	 	border: none;
	  	padding: 10px;
	  	width: 250px;
	  	background-color: #123456;
	 	margin-bottom: 30px;
	 	color: #ffffff;
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
	

	/* footer */
	footer {
	  	position: fixed;
	  	bottom: 0; left: 0;
	  	height: 100px;
	  	width: 100%;
	  	background-color: #ddd;
	}
	
	marquee{
		display:flex;
		width:100%;
		height:100px;
		font-family:monospace;
		color:white;
		align-items:center;/* display: flex 이후 세로 중앙정렬  */
	}
	
	#footer_m { 
		font-size:50px;
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
				      <a href="joinus">회원가입하기!</a>
				    </div>
			    </div><!-- login-box  -->
		 	  </div><!-- login-form  -->
		  </div><!-- item -->
		      <div class="item" id="item_1"></div><!-- 사연게시판  -->
		      <div class="item" id="item_2">자유게시판</div>
		      <div class="item" id="item_3">공지사항</div>
	    </div><!--carousel-container y-scroll  -->
	</div> <!-- inner-container -->
</main>

<footer>
	<marquee width="100%" direction="left" loop="-1" scrolldelay="50" bgcolor="black">
		<span id="footer_m">김동균의 개인 프로젝트입니다! 놀러오신걸 환영합니다.</span>
	</marquee>
</footer>
</body>
</html>