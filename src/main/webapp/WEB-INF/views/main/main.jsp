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
	body { margin:0 auto; width: 1130px;}
	header { display:flex;
			 justify-content: space-between;
			 background:#123456;}
			 
	header h1 { font-size:4rem; padding-left:1rem; }
	header h1 a { text-decoration: none;  color:#abcedf;  }
	
	nav { padding-top:10px;}
	nav ul { list-style: none; display: flex;}	

	nav ul li a { text-decoration: none;  color:#abcedf;}
	
	#container {
    position: relative;
    width: 1130px;
    padding-top:2rem;
    margin: 0 auto;
    letter-spacing: -.2px;
	}
	
	
	.sc_login {  background-color: #f7f9fa;
   				 border: 1px solid #dae1e6;
   				 width:230px;
   				 height:130px;
   				 position: relative;}
    .sc_login h2 { display:none;}		
    .btn btn-primary btn-lg { position: absolute;
								margin: auto;
								top: 0;
								left: 0;
								right: 0;
								bottom: 0;}	
    .look_box {display:inline-block;}
    .link_join {float:right;}
</style>
</head>
<body>

<header>
	<h1><a href="main">Music Story</a></h1>
	<nav>
		<ul>
			<li><a href="#">사연게시판</a></li>&nbsp;&nbsp;
			<li><a href="#">펌게시판</a></li>&nbsp;&nbsp;
			<li><a href="#">자유게시판</a></li>&nbsp;&nbsp;
		</ul>
	</nav>
</header>

<main>
<div id="container">
	<div id="account" class="sc_login">
		<h2 class="blind">로그인</h2>
		<a href="joinus">MusicStory 로그인</a>
	</div> <!-- sc_login  -->
</div><!-- container  -->
</main>

<footer>
</footer>
</body>
</html>