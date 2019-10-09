<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Internal server error</title>
</head>
<body>
<div align="center">
	<div>
		<img src="${pageContext.request.contextPath}/images/BookstoreLogo.png"/>
	</div>
	<div>
		<h2>Server error</h2>
		<h1>Please check back later!</h1>
	</div>
	
	<div>
		<a href="javascript:history.go(-1);">Go Back</a>
	</div>
</div>	
</body>
</html>