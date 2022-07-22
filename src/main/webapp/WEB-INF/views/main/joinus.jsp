<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="resources/myLib/jquery-3.2.1.min.js"></script>
<meta charset="UTF-8">
<title>** Music Story **</title>
<style>
	* { margin: 0; padding: 0; border: 0; }
	html{height: 100%;}
	body { background-image:url("resources/images/main_background.jpg"); }
  		   
	#logo { text-align:center;
		    font-size:5rem;}
		   
	#logo a {text-decoration: none;
			color:#6495ED;}	   
		   
	#container { margin: 0 auto; width: 600px;
				 border:3px solid blue;
				 border-radius: 30px 30px 30px 30px;
				 padding:10px;
				 line-height:30px;
				 background-color:	#E0FFFF;}
	.contentName { font-size: 1.2rem;}
	input { border-radius: 15px 15px 15px 15px; width: 600px; height: 50px; border: 2px solid lightgray; font-size: 15px }			 
	.select { width: 600px; height: 50px; ; font-size: 15px; border: 2px solid lightgray;}
	.birth { border-radius: 15px 15px 15px 15px; width: 190px; height: 50px; border: 2px solid lightgray; }
	.tech { height: 110px; }
	
	#male, #female { width:50px; height: 15px;}
	.radio_box	{ display: inline-block; }
	
	/* 모달창 가져오기  */
	 button {
          background-color:#AFEEEE; 
          padding: 5px 10px;
          border-radius: 4px;
          cursor: pointer;
        }

        .modal {
          position: fixed;
          top: 0;
          left: 0;
          width: 100%;
          height: 100%;
          display: flex;
          justify-content: center;
          align-items: center;
        }

        .modal .bg {
          width: 100%;
          height: 100%;
          background-color: rgba(0, 0, 0, 0.6);
        }

        .modalBox {
          position: absolute;
          background-color: #fff;
          width: 400px;
          height: 200px;
          padding: 15px;
          border-radius: 20px 20px 20px 20px;
          display:flex;
          align-items: center;
        }

        .modalBox button {
          position: absolute;
          display: block;
          width: 80px;
          margin: 0 auto;
          bottom: 20px;
          left: 170px;
        }

        .hidden {
          display: none;
        }
</style>

</head>
<body>
<div id="logo"><b><a href="main">Join Us</a></b></div>
<div id="container">
	<form action="join" method="post">
	<fieldset>
		<div>
			<span class="contentName">아이디</span> <br>
			<input type="text" name="id" maxlength="15" placeholder="15글자 이내로 입력하세요."> <br>
		</div>
		<div>
			<span class="contentName">비밀번호</span> <br>
			<input type="password" name="password"; maxlength="20" placeholder="소문자,대문자,특수문자가 모두 포함되어야합니다.">
		</div>
		<div>
			<span class="contentName">비밀번호 재확인</span> <br>
			<input type="text" name="passwordCheck" maxlength="20" >
		</div>
		<div>
			<span class="contentName">이름</span> <br>
			<input type="text" name="name" maxlength="15" >
		</div>
		<div>
			<span class="contentName">생년월일</span> <br>
			<input type="date" name="birth">
	<!-- 		<input class="birth" type="text" name="birthYear" maxlength="4" placeholder="년(4자)"> 
			<select class="birth" name="birth" >
				<option value="월">월</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>
			<input class="birth" name="birthDay" type="text"  maxlength="2" placeholder="일")>  -->
		</div>
		<div class="radio_box">  
			<span class="contentName">성별</span>
			<input  type="radio" name="gender" value="f" checked id="female"><label for="female">여성</label>&nbsp;&nbsp; 
			<input  type="radio" name="gender" value="m" id="male"><label for="male">남성</label>
		</div>
		</fieldset>	
		<input type="submit" class="join" value="가입하기">
	</form>
	
	<!-- 모달창 input에 입력하기 -->
	
	<div class="modal hidden">
	  <div class="bg"></div>
	  <div class="modalBox">
		  <p align="center"> 정말 가입하시겠습니까!?!
		    <button class="closeBtn">✖</button>
	  </div> 
	</div> <!-- modal hidden -->

</div>
</body>
<script>
	const open = () => {
	    document.querySelector(".modal").classList.remove("hidden");
	  }
	
	  const close = () => {
	    document.querySelector(".modal").classList.add("hidden");
	  }
	
	  document.querySelector(".join").addEventListener("click", open);
	  document.querySelector(".closeBtn").addEventListener("click", close);
	  document.querySelector(".bg").addEventListener("click", close);
</script>
</html>