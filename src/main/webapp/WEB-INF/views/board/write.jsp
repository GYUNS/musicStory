<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성하기</title>
</head>
<body>
<h2> 글 작성 해보기</h2>
<form	action ="binsert" method="get">
<table>
	<tr height="40"><td  bgcolor = "lightgreen">I D</td>
		<td><input type="text" name="id" value="${LoginID}" readonly></td>
	</tr>
	<tr height="40"><td  bgcolor = "lightgreen">Title</td>
		<td><input type="text" name="title"></td>
	</tr>
	<tr><td bgcolor="LightGreen">Content</td>
		<td><textarea rows="5" cols="50" name="content"></textarea></td>
	</tr>
	<tr>
	<td></td>
	<td><input type="submit" value="글등록"> &nbsp;&nbsp;
		<input type="reset" value="취소"></td>
	</tr>
</table>
</form>
<c:if test="${not empty message }">

<b>=> ${message}</b>
</c:if>
&nbsp;&nbsp; <a href="main">HOME</a>
&nbsp;&nbsp; <a href="main/#item_2">자유게시판</a>
</body>
</html>